package code.monsters.act1.allyPokemon;

import code.BetterSpriterAnimation;
import code.PokemonRegions;
import code.cards.AbstractAllyPokemonCard;
import code.monsters.AbstractPokemonAlly;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static code.PokemonRegions.makeMonsterPath;
import static code.util.Wiz.*;

public class Dragonite extends AbstractPokemonAlly
{
    public static final String ID = PokemonRegions.makeID(Dragonite.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;

    public Dragonite(final float x, final float y, AbstractAllyPokemonCard allyCard) {
        super(NAME, ID, 100, -5.0F, 0, 170.0f, 150.0f, null, x, y);
        this.animation = new BetterSpriterAnimation(makeMonsterPath("Dragonite/Dragonite.scml"));
        this.animation.setFlip(true, false);

        this.allyCard = allyCard;
        setStaminaInfo(allyCard);

        move1Intent = Intent.BUFF;
        move2Intent = Intent.ATTACK;
        addMove(MOVE_1, move1Intent);
        addMove(MOVE_2, move2Intent, code.cards.pokemonAllyCards.Dragonite.MOVE_2_DAMAGE);
        defaultMove = MOVE_2;
        move2RequiresTarget = true;
    }

    @Override
    public void takeTurn() {
        super.takeTurn();
        if(info.base > -1) {
            info.applyPowers(this, target);
        }
        switch (this.nextMove) {
            case MOVE_1: {
                applyToTarget(adp(), this, new StrengthPower(adp(), code.cards.pokemonAllyCards.Dragonite.MOVE_1_BUFF));
                break;
            }
            case MOVE_2: {
                dmg(target, info, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
                int newDamage = moves.get(MOVE_2).baseDamage += code.cards.pokemonAllyCards.Dragonite.MOVE_2_DAMAGE_INCREASE;
                addMove(MOVE_2, Intent.ATTACK, newDamage);
                break;
            }
        }
        postTurn();
    }

}