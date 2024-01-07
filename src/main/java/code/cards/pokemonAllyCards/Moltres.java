package code.cards.pokemonAllyCards;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import code.cards.AbstractAllyPokemonCard;
import code.monsters.AbstractPokemonAlly;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static code.PokemonRegions.makeID;

@NoPools
public class Moltres extends AbstractAllyPokemonCard {
    public final static String ID = makeID(Moltres.class.getSimpleName());
    public static final int MOVE_1_DAMAGE = 7;
    public static final int MOVE_1_BURN = 2;
    public static final int MOVE_2_EXHAUST = 3;
    public static final int MOVE_1_STAMINA_COST = 1;
    public static final int MOVE_2_STAMINA_COST = 0;
    public static final int MAX_STAMINA = 5;

    public Moltres() {
        super(ID, CardRarity.RARE);
        this.staminaCost1 = MOVE_1_STAMINA_COST;
        this.staminaCost2 = MOVE_2_STAMINA_COST;
        this.misc = this.maxStamina = this.currentStamina = MAX_STAMINA;
        this.move1Description = DESCRIPTIONS[2] + MOVE_1_DAMAGE + DESCRIPTIONS[3] + MOVE_1_BURN + DESCRIPTIONS[4];
        this.move2Description = DESCRIPTIONS[5] + MOVE_2_EXHAUST + DESCRIPTIONS[6];
        initializeDescriptionFromMoves();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public AbstractPokemonAlly getAssociatedPokemon(float x, float y) {
        return new code.monsters.act1.allyPokemon.Moltres(x, y, this);
    }
}