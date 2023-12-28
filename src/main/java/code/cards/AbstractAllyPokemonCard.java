package code.cards;

import code.PokemonRegions;
import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class AbstractAllyPokemonCard extends AbstractEasyCard {

    public int staminaCost1;
    public int staminaCost2;
    public int currentStamina;
    public int maxStamina;

    public AbstractAllyPokemonCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(cardID, cost, type, rarity, target, PokemonRegions.Enums.Pokedex);
    }

    public AbstractCard makeStatEquivalentCopy() {
        AbstractAllyPokemonCard c = (AbstractAllyPokemonCard)super.makeStatEquivalentCopy();
        c.staminaCost1 = staminaCost1;
        c.staminaCost2 = staminaCost2;
        c.currentStamina = currentStamina;
        c.maxStamina = maxStamina;
        return c;
    }
}
