package cards;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import javax.smartcardio.Card;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;

public class PassionlipRip
        extends CustomCard {
    public static final String ID = "alterego_mod:Rip";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "images/cards/Rip.png";
    private static final int COST = 0;

    public PassionlipRip() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.STATUS, Passionlip_Purple,
                CardRarity.COMMON, CardTarget.SELF);
        AutoplayField.autoplay.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1)));
        this.addToBot(new ApplyPowerAction(p, p, new FrailPower(p, 1, false )));
        if (this.upgraded) {
            this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, 5)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipRip();
    }

    @Override
    public boolean canUpgrade() {
        return !this.upgraded;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}