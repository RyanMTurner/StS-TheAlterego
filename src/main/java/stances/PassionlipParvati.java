package stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;
import vfx.AlteregoParvatiParticleEffect;
import vfx.AlteregoStanceAuraEffect;

import java.util.*;

public class PassionlipParvati extends AbstractStance {
    public static final String STANCE_ID = "alterego_mod:Parvati";
    private static final StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    public static final String NAME = stanceString.NAME;
    public static final String[] DESCRIPTIONS = stanceString.DESCRIPTION;

    private static float TIMER = 0.1F;

    boolean tookTriggerDamage = false;
    ArrayList<AbstractCreature> creaturesToStun;

    public PassionlipParvati() {
        this.ID = STANCE_ID;
        this.name = NAME;
        this.particleTimer = TIMER;
        this.updateDescription();
        creaturesToStun = new ArrayList<>();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        for (int i = 0; i < creaturesToStun.size(); i++) {
            if (creaturesToStun.get(i) instanceof AbstractMonster) {
                AbstractDungeon.actionManager.addToBottom(new StunMonsterAction((AbstractMonster) creaturesToStun.get(i), AbstractDungeon.player));
            }
        }
        creaturesToStun.clear();
    }

    public void onDamaged(DamageInfo info) {
        if (AbstractDungeon.player.lastDamageTaken >= 10) {
            if (!creaturesToStun.contains(info.owner)) {
                creaturesToStun.add(info.owner);
            }
        }
    }

    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.05F;
                AbstractDungeon.effectsQueue.add(new AlteregoParvatiParticleEffect());
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.3F, 0.4F);
            AbstractDungeon.effectsQueue.add(new AlteregoStanceAuraEffect(STANCE_ID));
        }

    }
}
