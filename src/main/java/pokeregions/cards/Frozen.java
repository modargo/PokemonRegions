package pokeregions.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pokeregions.PokemonRegions;

public class Frozen extends AbstractEasyCard {

    public static final String ID = PokemonRegions.makeID(Frozen.class.getSimpleName());

    public Frozen() {
        super(ID, 1, CardType.STATUS, CardRarity.COMMON, CardTarget.NONE, CardColor.COLORLESS);
        exhaust = true;
        setMagic(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ExhaustAction(magicNumber, false));
    }

    @Override
    public void upgrade() {
    }
}
