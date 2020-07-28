package relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import powers.AlteregoTempInvincible;

public class FleetingLoveRelic extends CustomRelic {
    public static final String ID = "alterego_mod:FleetingLove";

    public FleetingLoveRelic() {
        super(ID,
                new Texture("images/relics/Fleeting Love.png"), // you could create the texture in this class if you wanted too
                RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new AlteregoTempInvincible(AbstractDungeon.player, 6)));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new FleetingLoveRelic();
    }
}
