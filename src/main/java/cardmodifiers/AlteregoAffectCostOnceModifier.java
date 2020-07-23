package cardmodifiers;

import alterego_mod.AlteregoMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import basemod.abstracts.AbstractCardModifier;

public class AlteregoAffectCostOnceModifier extends AbstractCardModifier {

    int additionalCost = 0;

    public AlteregoAffectCostOnceModifier(int additionalCost) {
        this.additionalCost = additionalCost;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (card.cost > -1) {
            card.updateCost(additionalCost);
        }
    }

    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
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
