package pokeregions.monsters.act2.allyPokemon;

import com.brashmonkey.spriter.Player;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import pokeregions.BetterSpriterAnimation;
import pokeregions.PokemonRegions;
import pokeregions.cards.AbstractAllyPokemonCard;
import pokeregions.monsters.AbstractPokemonAlly;
import pokeregions.powers.Courage;

import static pokeregions.PokemonRegions.makeMonsterPath;
import static pokeregions.util.Wiz.applyToTarget;
import static pokeregions.util.Wiz.dmg;

public class Azumarill extends AbstractPokemonAlly
{
    public static final String ID = PokemonRegions.makeID(Azumarill.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;

    public Azumarill(final float x, final float y, AbstractAllyPokemonCard allyCard) {
        super(NAME, ID, 100, -5.0F, 0, 130.0f, 100.0f, null, x, y);
        this.animation = new BetterSpriterAnimation(makeMonsterPath("Azumarill/Azumarill.scml"));
        this.animation.setFlip(true, false);
        Player.PlayerListener listener = new PokemonListener(this);
        ((BetterSpriterAnimation)this.animation).myPlayer.addListener(listener);

        this.allyCard = allyCard;
        setStaminaInfo(allyCard);

        move1Intent = Intent.BUFF;
        move2Intent = Intent.ATTACK;
        addMove(MOVE_1, move1Intent);
        addMove(MOVE_2, move2Intent, pokeregions.cards.pokemonAllyCards.act2.Azumarill.MOVE_2_DAMAGE);
        defaultMove = MOVE_1;
        move2RequiresTarget = true;
    }

    @Override
    public void takeTurn() {
        super.takeTurn();
        switch (this.nextMove) {
            case MOVE_1: {
                applyToTarget(this, this, new Courage(this, pokeregions.cards.pokemonAllyCards.act2.Azumarill.MOVE_1_EFFECT));
                break;
            }
            case MOVE_2: {
                useFastAttackAnimation();
                dmg(target, info, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
                break;
            }
        }
        postTurn();
    }
}