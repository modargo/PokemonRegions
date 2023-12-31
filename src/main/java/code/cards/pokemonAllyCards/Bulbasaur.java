package code.cards.pokemonAllyCards;

import code.cards.AbstractAllyPokemonCard;
import code.monsters.AbstractPokemonAlly;
import code.util.Tags;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.PokemonRegions.makeID;

public class Bulbasaur extends AbstractAllyPokemonCard {
    public final static String ID = makeID(Bulbasaur.class.getSimpleName());

    public static final int MOVE_1_DAMAGE = 5;
    public static final int MOVE_2_TOXIC = 3;
    public static final int MOVE_2_WEAK = 1;
    public static final int MOVE_1_STAMINA_COST = 0;
    public static final int MOVE_2_STAMINA_COST = 1;
    public static final int MAX_STAMINA = 6;

    public Bulbasaur() {
        super(ID, CardRarity.BASIC);
        tags.add(Tags.STARTER_POKEMON);
        this.staminaCost1 = MOVE_1_STAMINA_COST;
        this.staminaCost2 = MOVE_2_STAMINA_COST;
        this.misc = this.maxStamina = this.currentStamina = MAX_STAMINA;
        this.move1Name = cardStrings.EXTENDED_DESCRIPTION[0];
        this.move2Name = cardStrings.EXTENDED_DESCRIPTION[1];
        this.move1Description = cardStrings.EXTENDED_DESCRIPTION[2] + MOVE_1_DAMAGE + cardStrings.EXTENDED_DESCRIPTION[3];
        this.move2Description = cardStrings.EXTENDED_DESCRIPTION[4] + MOVE_2_TOXIC + cardStrings.EXTENDED_DESCRIPTION[5] + MOVE_2_WEAK + cardStrings.EXTENDED_DESCRIPTION[6];
        initializeDescriptionFromMoves();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public AbstractPokemonAlly getAssociatedPokemon(float x, float y) {
        return new code.monsters.act1.allyPokemon.Bulbasaur(x, y, this);
    }
}