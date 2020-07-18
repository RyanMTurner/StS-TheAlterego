package patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.stances.AbstractStance;
import stances.PassionlipBrynhildr;
import stances.PassionlipDurga;
import stances.PassionlipParvati;

public class AbstractStancePatch {

    @SpirePatch(clz = AbstractStance.class,
            method = "getStanceFromName")
    public static class getStanceFromName {
        public static SpireReturn<AbstractStance> Prefix(String stanceID) {
            if (stanceID.equals(PassionlipDurga.STANCE_ID)) {
                return SpireReturn.Return(new PassionlipDurga());
            }
            if (stanceID.equals(PassionlipBrynhildr.STANCE_ID)) {
                return SpireReturn.Return(new PassionlipBrynhildr());
            }
            if (stanceID.equals(PassionlipParvati.STANCE_ID)) {
                return SpireReturn.Return(new PassionlipParvati());
            }

            return SpireReturn.Continue();
        }
    }
}