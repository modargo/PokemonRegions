package pokeregions.monsters.act1.enemies;

import basemod.ReflectionHacks;
import com.brashmonkey.spriter.Player;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.WeakPower;
import pokeregions.BetterSpriterAnimation;
import pokeregions.PokemonRegions;
import pokeregions.cards.pokemonAllyCards.act1.Caterpie;
import pokeregions.monsters.AbstractPokemonMonster;
import pokeregions.util.Details;

import java.util.ArrayList;

import static pokeregions.PokemonRegions.*;
import static pokeregions.util.Wiz.*;

public class CaterpieEnemy extends AbstractPokemonMonster
{
    public static final String ID = makeID(CaterpieEnemy.class.getSimpleName());
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;

    private static final byte STRING_SHOT = 0;
    private static final byte BUG_BITE = 1;

    public final int STATUS = calcAscensionSpecial(1);
    public final int DEBUFF = 1;

    public CaterpieEnemy() {
        this(0.0f, 0.0f);
    }

    public CaterpieEnemy(final float x, final float y) {
        super(NAME, ID, 140, 0.0F, 0, 110.0f, 80.0f, null, x, y);
        this.animation = new BetterSpriterAnimation(makeMonsterPath("Caterpie/Caterpie.scml"));
        this.type = EnemyType.NORMAL;
        setHp(calcAscensionTankiness(15), calcAscensionTankiness(18));
        addMove(STRING_SHOT, Intent.DEBUFF);
        addMove(BUG_BITE, Intent.ATTACK, calcAscensionDamage(5));

        Player.PlayerListener listener = new PokemonListener(this);
        ((BetterSpriterAnimation)this.animation).myPlayer.addListener(listener);
    }

    @Override
    public void takeTurn() {
        super.takeTurn();
        if(info.base > -1) {
            info.applyPowers(this, adp());
        }

        switch (this.nextMove) {
            case STRING_SHOT: {
                applyToTarget(adp(), this, new WeakPower(adp(), DEBUFF, true));
                intoDiscardMo(new Slimed(), STATUS);
                break;
            }
            case BUG_BITE: {
                useFastAttackAnimation();
                dmg(adp(), info, AbstractGameAction.AttackEffect.POISON);
                break;
            }
        }
        atb(new RollMoveAction(this));
    }

    @Override
    protected void getMove(final int num) {
        ArrayList<Byte> possibilities = new ArrayList<>();
        if (!this.lastTwoMoves(STRING_SHOT)) {
            possibilities.add(STRING_SHOT);
        }
        if (!this.lastTwoMoves(BUG_BITE)) {
            possibilities.add(BUG_BITE);
        }
        byte move = possibilities.get(AbstractDungeon.monsterRng.random(possibilities.size() - 1));
        setMoveShortcut(move, MOVES[move]);

        super.postGetMove();
    }

    protected void setDetailedIntents() {
        ArrayList<Details> details = new ArrayList<>();
        EnemyMoveInfo move = ReflectionHacks.getPrivate(this, AbstractMonster.class, "move");
        switch (move.nextMove) {
            case STRING_SHOT: {
                Details powerDetail = new Details(this, DEBUFF, WEAK_TEXTURE);
                details.add(powerDetail);
                Details statusDetail = new Details(this, STATUS, SLIMED_TEXTURE, Details.TargetType.DISCARD_PILE);
                details.add(statusDetail);
                break;
            }
        }
        PokemonRegions.intents.put(this, details);
    }

    @Override
    public AbstractCard getAssociatedPokemonCard() {
        return new Caterpie();
    }

}