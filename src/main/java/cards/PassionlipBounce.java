package cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import basemod.abstracts.CustomCard;

import java.util.function.Predicate;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;

public class PassionlipBounce
        extends CustomCard {
    public static final String ID = "alterego_mod:Bounce";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "images/cards/Bounce.png";
    private static final int COST = 0;

    public PassionlipBounce() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.ATTACK, Passionlip_Purple,
                CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        for (int i = 0; i < p.drawPile.size(); i++) {
            if (p.drawPile.getNCardFromTop(i) instanceof PassionlipBounce) {
                this.addToBot(new MoveCardsAction(AbstractDungeon.player.hand, AbstractDungeon.player.drawPile, new CardPredicate(p.drawPile.getNCardFromTop(i))));
                break;
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipBounce();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }

    private class CardPredicate implements Predicate<AbstractCard> {

        AbstractCard self;

        public CardPredicate(AbstractCard self) {
            this.self = self;
        }

        @Override
        public boolean test(AbstractCard other) {
            return other.uuid == self.uuid;
        }
    }
}