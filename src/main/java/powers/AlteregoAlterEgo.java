package powers;

import alterego_mod.AlteregoMod;
import basemod.helpers.CardModifierManager;
import cardmodifiers.AlteregoAffectCostTurnModifier;
import cardmodifiers.AlteregoCloneModifier;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import util.TextureLoader;

import java.util.ArrayList;

public class AlteregoAlterEgo extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = "alterego_mod:AlterEgoPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    private static final Texture tex84 = TextureLoader.getTexture("images/powers/AlterEgo_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("images/powers/AlterEgo_32.png");

    ArrayList<AbstractCard> retainedCards;
    int applicationsThisTurn;

    public AlteregoAlterEgo(final AbstractCreature owner, final AbstractCreature source) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = 1;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        retainedCards = new ArrayList<AbstractCard>();
        applicationsThisTurn = 1;

        updateDescription();
    }

    private void applyModifierIfApplicable(AbstractCard card) {
        int amt = this.amount - applicationsThisTurn;
        if (amt > 0) {
            if (GameActionManager.turn % 2 == 0 && card.type == AbstractCard.CardType.SKILL) {
                CardModifierManager.addModifier(card, new AlteregoAffectCostTurnModifier(-amt));
            }
            else if (GameActionManager.turn % 2 == 1 && card.type == AbstractCard.CardType.ATTACK) {
                CardModifierManager.addModifier(card, new AlteregoAffectCostTurnModifier(-amt));
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        applicationsThisTurn = 0;
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        applyModifierIfApplicable(card);
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        applicationsThisTurn += stackAmount;
    }

    @Override
    public void atStartOfTurn() {
        //Handle retained cards
        retainedCards.clear();
        for (int i = 0; i < AbstractDungeon.player.hand.size(); i++) {
            retainedCards.add(AbstractDungeon.player.hand.getNCardFromTop(i));
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        //Handle retained cards
        for (int i = 0; i < retainedCards.size(); i++) {
            applyModifierIfApplicable(retainedCards.get(i));
        }
        retainedCards.clear();

        //Update turn reminder
        updateDescription();
    }

    /*
    @Override
    public void onInitialApplication() {
        for (int i = 0; i < AbstractDungeon.player.hand.size(); i++) {
            applyModifierIfApplicable(AbstractDungeon.player.hand.getNCardFromTop(i));
        }
    }
    */

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2] + GameActionManager.turn + ")";
        if (applicationsThisTurn > 0) {
            this.description += " NL (" + applicationsThisTurn + (applicationsThisTurn == 1 ? DESCRIPTIONS[3] : DESCRIPTIONS[4]);
        }
    }
}