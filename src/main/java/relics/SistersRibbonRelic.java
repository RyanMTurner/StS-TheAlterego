package relics;

import alterego_mod.AlteregoMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
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

public class SistersRibbonRelic extends CustomRelic implements ClickableRelic {
    public static final String ID = "alterego_mod:SistersRibbon";
    boolean usedThisCombat = false;

    public SistersRibbonRelic() {
        super(ID,
                new Texture("images/relics/Sisters Ribbon.png"), // you could create the texture in this class if you wanted too
                RelicTier.SHOP, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return CLICKABLE_DESCRIPTIONS()[0] + DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void atBattleStart() {
        usedThisCombat = false;
        this.pulse = true;
        this.beginPulse();
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new SistersRibbonRelic();
    }

    @Override
    public void onRightClick() {
        if (AbstractDungeon.player.exhaustPile.size() > 0 && !usedThisCombat) {
            this.addToBot(new ExhumeAction(false));
            usedThisCombat = true;
            this.pulse = false;
            this.stopPulse();
        }
    }
}
