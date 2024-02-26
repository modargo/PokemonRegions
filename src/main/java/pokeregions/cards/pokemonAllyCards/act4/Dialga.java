package pokeregions.cards.pokemonAllyCards.act4;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import pokeregions.cards.AbstractAllyPokemonCard;
import pokeregions.monsters.AbstractPokemonAlly;

import static pokeregions.PokemonRegions.makeID;

@NoPools
public class Dialga extends AbstractAllyPokemonCard {
    public final static String ID = makeID(Dialga.class.getSimpleName());
    public static final int MOVE_1_EFFECT = 20;

    public static final int MOVE_1_STAMINA_COST = 3;
    public static final int MOVE_2_STAMINA_COST = 1;
    public static final int MAX_STAMINA = 5;

    public Dialga() {
        super(ID, CardRarity.RARE);
        this.staminaCost1 = MOVE_1_STAMINA_COST;
        this.staminaCost2 = MOVE_2_STAMINA_COST;
        this.misc = this.maxStamina = this.currentStamina = MAX_STAMINA;
        this.move1Description = DESCRIPTIONS[2] + MOVE_1_EFFECT + DESCRIPTIONS[3];
        this.move2Description = DESCRIPTIONS[4];
        initializeDescriptionFromMoves();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public AbstractPokemonAlly getAssociatedPokemon(float x, float y) {
        return new pokeregions.monsters.act4.allyPokemon.Dialga(x, y, this);
    }
}