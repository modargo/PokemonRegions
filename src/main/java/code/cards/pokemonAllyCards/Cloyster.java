package code.cards.pokemonAllyCards;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import code.cards.AbstractAllyPokemonCard;
import code.monsters.AbstractPokemonAlly;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.PokemonRegions.makeID;

@NoPools
public class Cloyster extends AbstractAllyPokemonCard {
    public final static String ID = makeID(Cloyster.class.getSimpleName());
    public static final int MOVE_2_DAMAGE = 4;
    public static final int MOVE_2_HITS = 3;
    public static final int MOVE_1_STAMINA_COST = 3;
    public static final int MOVE_2_STAMINA_COST = 1;
    public static final int MAX_STAMINA = 5;

    public Cloyster() {
        super(ID, CardRarity.UNCOMMON);
        this.staminaCost1 = MOVE_1_STAMINA_COST;
        this.staminaCost2 = MOVE_2_STAMINA_COST;
        this.misc = this.maxStamina = this.currentStamina = MAX_STAMINA;
        this.move1Description = DESCRIPTIONS[2];
        this.move2Description = DESCRIPTIONS[3] + MOVE_2_DAMAGE + DESCRIPTIONS[4] + MOVE_2_HITS + DESCRIPTIONS[5];
        initializeDescriptionFromMoves();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public AbstractPokemonAlly getAssociatedPokemon(float x, float y) {
        return new code.monsters.act1.allyPokemon.Cloyster(x, y, this);
    }
}