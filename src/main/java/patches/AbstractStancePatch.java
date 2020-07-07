package patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.stances.AbstractStance;
import stances.PassionlipDurga;

public class AbstractStancePatch {

    @SpirePatch(clz = AbstractStance.class,
            method = "getStanceFromName")
    public static class getStanceFromName {
        public static SpireReturn<AbstractStance> Prefix(String stanceID) {
            if (stanceID.equals(PassionlipDurga.STANCE_ID)) {
                return SpireReturn.Return(new PassionlipDurga());
            }

            return SpireReturn.Continue();
        }
    }
}