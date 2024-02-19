package pokeregions.monsters.act3.allyPokemon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import pokeregions.BetterSpriterAnimation;
import pokeregions.PokemonRegions;
import pokeregions.cards.AbstractAllyPokemonCard;
import pokeregions.monsters.AbstractPokemonAlly;
import pokeregions.powers.MistBall;
import pokeregions.util.ProAudio;
import pokeregions.util.Wiz;
import pokeregions.vfx.ThrowEffect;

import static pokeregions.PokemonRegions.makeMonsterPath;
import static pokeregions.util.Wiz.*;

public class Latias extends AbstractPokemonAlly
{
    public static final String ID = PokemonRegions.makeID(Latias.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public Latias(final float x, final float y, AbstractAllyPokemonCard allyCard) {
        super(NAME, ID, 100, -5.0F, 0, 160.0f, 120.0f, null, x, y);
        this.animation = new BetterSpriterAnimation(makeMonsterPath("Latias/Latias.scml"));
        this.animation.setFlip(true, false);

        this.allyCard = allyCard;
        setStaminaInfo(allyCard);

        move1Intent = Intent.DEFEND_BUFF;
        move2Intent = Intent.ATTACK_DEBUFF;
        addMove(MOVE_1, move1Intent);
        addMove(MOVE_2, move2Intent, pokeregions.cards.pokemonAllyCards.act3.Latias.MOVE_2_DAMAGE);
        defaultMove = MOVE_1;
        move2RequiresTarget = true;
    }

    @Override
    public void takeTurn() {
        super.takeTurn();
        switch (this.nextMove) {
            case MOVE_1: {
                block(adp(), pokeregions.cards.pokemonAllyCards.act3.Latias.MOVE_1_BLOCK);
                atb(new ScryAction(pokeregions.cards.pokemonAllyCards.act3.Latias.MOVE_1_EFFECT));
                break;
            }
            case MOVE_2: {
                useFastAttackAnimation();
                float duration = 0.5f;
                atb(new VFXAction(ThrowEffect.throwEffect("PurpleSpike.png", 1.0f, this.hb, target.hb, Color.PURPLE.cpy(), duration, false, true), duration));
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        Wiz.playAudio(ProAudio.MAGIC_BLAST, 1.0f);
                        this.isDone = true;
                    }
                });
                dmg(target, info, AbstractGameAction.AttackEffect.NONE);
                applyToTarget(target, this, new MistBall(target, pokeregions.cards.pokemonAllyCards.act3.Latias.MOVE_2_EFFECT, 1));
                break;
            }
        }
        postTurn();
    }

}