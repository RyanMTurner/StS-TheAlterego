package relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class MasochisticConstitutionRelic extends CustomRelic {
    public static final String ID = "alterego_mod:MasochisticConstitution";
    private boolean tookUnblockedDamageThisCombat = false;

    public MasochisticConstitutionRelic() {
        super(ID,
                new Texture("images/relics/Masochistic Constitution.png"), // you could create the texture in this class if you wanted too
                RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        this.tookUnblockedDamageThisCombat = false;
    }

    @Override
    public void onLoseHp(int damageAmount) {
        if (!this.tookUnblockedDamageThisCombat) {
            this.pulse = true;
            this.beginPulse();
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.tookUnblockedDamageThisCombat = true;
        }
    }

    @Override
    public void onVictory() {
        if (this.tookUnblockedDamageThisCombat) {
            this.flash();
            this.pulse = false;
            this.stopPulse();
            AbstractDungeon.player.increaseMaxHp(1, true);
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new MasochisticConstitutionRelic();
    }
}
