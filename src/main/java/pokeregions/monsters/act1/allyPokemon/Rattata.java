package pokeregions.monsters.act1.allyPokemon;

import pokeregions.BetterSpriterAnimation;
import pokeregions.PokemonRegions;
import pokeregions.cards.AbstractAllyPokemonCard;
import pokeregions.monsters.AbstractPokemonAlly;
import com.brashmonkey.spriter.Player;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

import static pokeregions.PokemonRegions.makeMonsterPath;
import static pokeregions.util.Wiz.*;

public class Rattata extends AbstractPokemonAlly
{
    public static final String ID = PokemonRegions.makeID(Rattata.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;

    public Rattata(final float x, final float y, AbstractAllyPokemonCard allyCard) {
        super(NAME, ID, 100, -5.0F, 0, 130.0f, 100.0f, null, x, y);
        this.animation = new BetterSpriterAnimation(makeMonsterPath("Rattata/Rattata.scml"));
        this.animation.setFlip(true, false);
        Player.PlayerListener listener = new PokemonListener(this);
        ((BetterSpriterAnimation)this.animation).myPlayer.addListener(listener);

        this.allyCard = allyCard;
        setStaminaInfo(allyCard);

        move1Intent = Intent.ATTACK_BUFF;
        move2Intent = Intent.DEFEND_BUFF;
        addMove(MOVE_1, move1Intent, pokeregions.cards.pokemonAllyCards.act1.Rattata.MOVE_1_DAMAGE);
        addMove(MOVE_2, move2Intent);
        defaultMove = MOVE_1;
        move1RequiresTarget = true;
    }

    @Override
    public void takeTurn() {
        super.takeTurn();
        switch (this.nextMove) {
            case MOVE_1: {
                useFastAttackAnimation();
                dmg(target, info, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
                atb(new DrawCardAction(adp(), pokeregions.cards.pokemonAllyCards.act1.Rattata.MOVE_1_DRAW));
                break;
            }
            case MOVE_2: {
                block(adp(), pokeregions.cards.pokemonAllyCards.act1.Rattata.MOVE_2_BLOCK);
                atb(new DrawCardAction(adp(), pokeregions.cards.pokemonAllyCards.act1.Rattata.MOVE_2_DRAW));
                break;
            }
        }
        postTurn();
    }

}