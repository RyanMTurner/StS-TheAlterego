package cards;

import alterego_mod.AlteregoMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.function.Predicate;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;

public class PassionlipHighServant
        extends CustomCard {
    public static final String ID = "alterego_mod:HighServant";
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    // Get object containing the strings that are displayed in the game.
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String IMG_PATH = "images/cards/HighServant.png";
    private static final int COST = 1;

    public static boolean usingHighServant = false;

    public PassionlipHighServant() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
                CardType.SKILL, Passionlip_Purple,
                CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!usingHighServant) { //Don't nest if there are multiple in your deck
            usingHighServant = true;
            for (int i = 0; i < p.drawPile.size(); i++) {
                if (testIfStance(p, p.drawPile.getNCardFromTop(i))) {
                    this.addToBot(new MoveCardsAction(AbstractDungeon.player.hand, AbstractDungeon.player.drawPile, new CardPredicate(p.drawPile.getNCardFromTop(i))));
                    break;
                }
            }
            usingHighServant = false;
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassionlipHighServant();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }

    private boolean testIfStance(AbstractPlayer p, AbstractCard card) {
        //https://discordapp.com/channels/309399445785673728/398373038732738570/734072777069297754
        //Make a dummy list
        ArrayList<AbstractGameAction> dummy = new ArrayList<>();

        //Swap it with the action manager's list
        ArrayList<AbstractGameAction> swapHolder = AbstractDungeon.actionManager.actions;
        AbstractDungeon.actionManager.actions = dummy;

        //Invoke the card's use
        boolean free = card.freeToPlayOnce;
        card.freeToPlayOnce = true;
        card.use(p, null);
        card.freeToPlayOnce = free;

        //Scan the list for a stance change action
        boolean changesStance = false;
        for (int i = 0; i < dummy.size(); i++) {
            if (dummy.get(i) instanceof ChangeStanceAction) {
                String action = getStanceId((ChangeStanceAction)dummy.get(i));
                if (action != "" && action != p.stance.ID) {
                    changesStance = true;
                    break;
                }
            }
        }

        //Swap the list back
        AbstractDungeon.actionManager.actions = swapHolder;

        return changesStance;
    }

    private String getStanceId(ChangeStanceAction action) {
        String id = "";
        try { //https://github.com/kiooeht/Hubris/blob/master/src/main/java/com/evacipated/cardcrawl/mod/hubris/actions/unique/CounterAction.java
            Field f = ChangeStanceAction.class.getDeclaredField("id");
            f.setAccessible(true);
            id = (String)f.get(action);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return id;
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