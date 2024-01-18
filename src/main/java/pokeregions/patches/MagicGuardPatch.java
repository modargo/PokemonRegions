package pokeregions.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import pokeregions.monsters.act1.enemies.AlakazamEnemy;

public class MagicGuardPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class NegateUseCard {
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getClassName().equals(AbstractCard.class.getName()) && m.getMethodName().equals("use")) {
                        m.replace("{" +
                                "if(!(" + MagicGuardPatch.class.getName() + ".NegateCardPlay(c, monster))) {" +
                                "$proceed($$);" +
                                "}" +
                                "}");
                    }
                }
            };
        }
    }

    public static boolean NegateCardPlay(AbstractCard c, AbstractMonster m) {
        if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().monsters != null) {
            for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (monster.hasPower(AlakazamEnemy.POWER_ID)) {
                    AbstractPower power = monster.getPower(AlakazamEnemy.POWER_ID);
                    return power.amount >= 1;
                }
            }
        }
        return false;
    }
}