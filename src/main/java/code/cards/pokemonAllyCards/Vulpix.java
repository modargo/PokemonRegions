package code.cards.pokemonAllyCards;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import code.cards.AbstractAllyPokemonCard;
import code.monsters.AbstractPokemonAlly;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.PokemonRegions.makeID;

@NoPools
public class Vulpix extends AbstractAllyPokemonCard {
    public final static String ID = makeID(Vulpix.class.getSimpleName());
    public static final int MOVE_1_DAMAGE = 4;
    public static final int MOVE_1_BURN = 1;
    public static final int MOVE_2_DAMAGE = 5;
    public static final int MOVE_2_HITS = 2;
    public static final int MOVE_1_STAMINA_COST = 1;
    public static final int MOVE_2_STAMINA_COST = 1;
    public static final int MAX_STAMINA = 4;

    public Vulpix() {
        super(ID, CardRarity.COMMON);
        this.staminaCost1 = MOVE_1_STAMINA_COST;
        this.staminaCost2 = MOVE_2_STAMINA_COST;
        this.misc = this.maxStamina = this.currentStamina = MAX_STAMINA;
        this.move1Description = DESCRIPTIONS[2] + MOVE_1_DAMAGE + DESCRIPTIONS[3] + MOVE_1_BURN + DESCRIPTIONS[4];
        this.move2Description = DESCRIPTIONS[5] + MOVE_2_DAMAGE + DESCRIPTIONS[6] + MOVE_2_HITS + DESCRIPTIONS[7];
        initializeDescriptionFromMoves();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public AbstractPokemonAlly getAssociatedPokemon(float x, float y) {
        return new code.monsters.act1.allyPokemon.Vulpix(x, y, this);
    }
}