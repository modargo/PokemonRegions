package code.monsters.act1.allyPokemon;

import code.BetterSpriterAnimation;
import code.PokemonRegions;
import code.cards.AbstractAllyPokemonCard;
import code.monsters.AbstractPokemonAlly;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.orbs.Frost;

import static code.PokemonRegions.makeMonsterPath;
import static code.util.Wiz.atb;

public class Articuno extends AbstractPokemonAlly
{
    public static final String ID = PokemonRegions.makeID(Articuno.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;

    public Articuno(final float x, final float y, AbstractAllyPokemonCard allyCard) {
        super(NAME, ID, 100, -5.0F, 0, 170.0f, 150.0f, null, x, y);
        this.animation = new BetterSpriterAnimation(makeMonsterPath("Articuno/Articuno.scml"));
        this.animation.setFlip(true, false);

        this.allyCard = allyCard;
        setStaminaInfo(allyCard);

        move1Intent = Intent.MAGIC;
        move2Intent = Intent.BUFF;
        addMove(MOVE_1, move1Intent);
        addMove(MOVE_2, move2Intent);
        defaultMove = MOVE_2;
    }

    @Override
    public void takeTurn() {
        super.takeTurn();
        switch (this.nextMove) {
            case MOVE_1: {
                for(int i = 0; i < code.cards.pokemonAllyCards.Articuno.MOVE_1_ORBS; ++i) {
                    atb(new ChannelAction(new Frost()));
                }
                break;
            }
            case MOVE_2: {
                atb(new ScryAction(code.cards.pokemonAllyCards.Articuno.MOVE_2_SCRY));
                break;
            }
        }
        postTurn();
    }

}