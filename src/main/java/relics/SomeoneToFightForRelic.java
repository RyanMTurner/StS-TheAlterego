package relics;

import alterego_mod.AlteregoMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AmplifyPower;
import com.megacrit.cardcrawl.powers.BurstPower;
import com.megacrit.cardcrawl.powers.DoubleTapPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.AlteregoTempInvincible;
import stances.PassionlipBrynhildr;
import stances.PassionlipDurga;
import stances.PassionlipParvati;

public class SomeoneToFightForRelic extends CustomRelic {
    public static final String ID = "alterego_mod:SomeoneToFightFor";

    public SomeoneToFightForRelic() {
        super(ID,
                new Texture("images/relics/Someone to Fight For.png"), // you could create the texture in this class if you wanted too
                RelicTier.SPECIAL, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        int stance = AbstractDungeon.cardRandomRng.random(0, 2);
        switch(stance) {
            case 0:
                AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction(PassionlipDurga.STANCE_ID));
                break;
            case 1:
                AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction(PassionlipBrynhildr.STANCE_ID));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction(PassionlipParvati.STANCE_ID));
                break;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new SomeoneToFightForRelic();
    }
}
