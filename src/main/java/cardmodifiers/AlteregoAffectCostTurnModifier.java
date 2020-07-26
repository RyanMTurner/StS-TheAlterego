package cardmodifiers;

import alterego_mod.AlteregoMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import basemod.abstracts.AbstractCardModifier;

public class AlteregoAffectCostTurnModifier extends AbstractCardModifier {

    int additionalCost = 0;

    public AlteregoAffectCostTurnModifier(int additionalCost) {
        this.additionalCost = additionalCost;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (card.cost > -1) {
            if (additionalCost < 0 && card.cost < -additionalCost) { //Card would become more expensive on removal if you lowered past 0
                additionalCost = -card.cost;
            }
            card.updateCost(additionalCost);
        }
    }

    @Override
    public boolean removeAtEndOfTurn(AbstractCard card) {
        return true;
    }

    @Override
    public void onRemove(AbstractCard card) {
        if (card.cost > -1) {
            card.updateCost(-additionalCost);
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AlteregoAffectCostOnceModifier(additionalCost);
    }
}
