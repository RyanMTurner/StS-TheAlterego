package powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import util.TextureLoader;

import java.util.ArrayList;

public class AlteregoInsideBaseball extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = "alterego_mod:InsideBaseballPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("images/powers/InsideBaseball_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("images/powers/InsideBaseball_32.png");

    public AlteregoInsideBaseball(final AbstractCreature owner, final AbstractCreature source, final int amount, final boolean turnBased) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        enemiesOut = new ArrayList<AbstractMonster>();

        type = PowerType.BUFF;
        isTurnBased = turnBased;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    ArrayList<AbstractMonster> enemiesOut;

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (this.amount > 0 && info.owner instanceof AbstractMonster && !enemiesOut.contains(info.owner)) {
            AbstractMonster attacker = (AbstractMonster)info.owner;
            if (attacker.type != AbstractMonster.EnemyType.BOSS) {
                enemiesOut.add(attacker);
                this.addToBot(new EscapeAction(attacker));
                AbstractDungeon.player.loseGold(30);
                this.addToBot(new ReducePowerAction(this.owner, this.owner, "alterego_mod:InsideBaseballPower", 1));
                return damageAmount;
            }
        }
        return damageAmount;
    }

    @Override
    public void atEndOfRound() {
        if (this.amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "alterego_mod:InsideBaseballPower"));
        } else if (isTurnBased) {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, "alterego_mod:InsideBaseballPower", 1));
        }

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}