package cards;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.EscapeAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Zap;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.potions.SmokeBomb;
import com.megacrit.cardcrawl.powers.*;

import basemod.abstracts.CustomCard;

import java.lang.reflect.Field;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;
import static com.megacrit.cardcrawl.monsters.AbstractMonster.Intent.ATTACK;

public class PassionlipMirror
        extends CustomCard {
    public static final String ID = "alterego_mod:Mirror";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "images/cards/Mirror.png";
    private static final int COST = 1;

    public PassionlipMirror() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, Passionlip_Purple,
                CardRarity.COMMON, CardTarget.ENEMY);
        this.baseBlock = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m == null) {
            return;
        }
        switch (m.intent) {
            case ATTACK:
                if (!this.upgraded) {
                    makeDamageActions(p, m, getIntentDmg(m));
                }
                else {
                    for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                        makeDamageActions(p, mo, getIntentDmg(m));
                    }
                }
                break;
            case ATTACK_BUFF:
                if (!this.upgraded) {
                    makeDamageActions(p, m, getIntentDmg(m));
                }
                else {
                    for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                        makeDamageActions(p, mo, getIntentDmg(m));
                    }
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 3)));
                break;
            case ATTACK_DEBUFF:
                if (!this.upgraded) {
                    makeDamageActions(p, m, getIntentDmg(m));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, 1, false)));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, 1, false)));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new FrailPower(m, 1, false)));
                }
                else {
                    for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                        makeDamageActions(p, mo, getIntentDmg(m));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, 1, false)));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 1, false)));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new FrailPower(mo, 1, false)));
                    }
                }
                break;
            case ATTACK_DEFEND:
                if (!this.upgraded) {
                    makeDamageActions(p, m, getIntentDmg(m));
                }
                else {
                    for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                        makeDamageActions(p, mo, getIntentDmg(m));
                    }
                }
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p,
                        p, getIntentDmg(m).damage));
                break;
            case BUFF:
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 3)));
                break;
            case DEBUFF:
                if (!this.upgraded) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, 1, false)));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, 1, false)));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new FrailPower(m, 1, false)));
                }
                else {
                    for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, 1, false)));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 1, false)));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new FrailPower(mo, 1, false)));
                    }
                }
                break;
            case STRONG_DEBUFF:
                if (!this.upgraded) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, 3, false)));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, 3, false)));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new FrailPower(m, 3, false)));
                }
                else {
                    for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, 3, false)));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 3, false)));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new FrailPower(mo, 3, false)));
                    }
                }
                break;
            case DEBUG:
                break;
            case DEFEND:
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p,
                        p, this.block));
                break;
            case DEFEND_DEBUFF:
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p,
                        p, this.block));
                if (!this.upgraded) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, 1, false)));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, 1, false)));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new FrailPower(m, 1, false)));
                }
                else {
                    for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, 1, false)));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 1, false)));
                        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new FrailPower(mo, 1, false)));
                    }
                }
                break;
            case DEFEND_BUFF:
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p,
                        p, this.block));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 3)));
                break;
            case ESCAPE:
                SmokeBomb s = new SmokeBomb();
                s.use(p);
                break;
            case MAGIC:
                AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Lightning()));
                break;
            case NONE:
                break;
            case SLEEP:
                break;
            case STUN:
                break;
            case UNKNOWN:
                break;
            default:
                break;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipMirror();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    private class IntentDmg {
        public int damage = 0;
        public int multi = 1;
    }

    private IntentDmg getIntentDmg(AbstractMonster m) {
        IntentDmg intentDmg = new IntentDmg();
        try { //https://github.com/kiooeht/Hubris/blob/master/src/main/java/com/evacipated/cardcrawl/mod/hubris/actions/unique/CounterAction.java
            Field f = AbstractMonster.class.getDeclaredField("move");
            f.setAccessible(true);
            EnemyMoveInfo move = (EnemyMoveInfo)f.get(m);
            if (move.isMultiDamage) {
                intentDmg.multi = move.multiplier;
            }

            f = AbstractMonster.class.getDeclaredField("intentDmg");
            f.setAccessible(true);
            move.baseDamage = f.getInt(m);

            intentDmg.damage = move.baseDamage;

        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return intentDmg;
    }

    private void makeDamageActions(AbstractPlayer p, AbstractMonster m, IntentDmg d) {
        for (int i = 0; i < d.multi; i++) {
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                    new DamageInfo(p, d.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }
}