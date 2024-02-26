package pokeregions.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import pokeregions.dungeons.SpearPillar;

@SpirePatch(clz = CardCrawlGame.class, method = "updateFade")
public class NoCutsceneAfterCreditsPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert() {
        if(AbstractDungeon.id == SpearPillar.ID) {
            CardCrawlGame.playCreditsBgm = false;
        }
    }


    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "reset");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}