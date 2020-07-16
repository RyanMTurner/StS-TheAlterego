package powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import util.TextureLoader;

public class AlteregoPunishmentUpgraded extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = "alterego_mod:PunishmentPowerUpgraded";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("images/powers/Punishment_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("images/powers/Punishment_32.png");

    public AlteregoPunishmentUpgraded(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            this.addToTop(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, AbstractDungeon.player.hand.size() / 2, AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}