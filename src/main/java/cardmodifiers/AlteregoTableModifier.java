package cardmodifiers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.AbstractCardModifier;

public class AlteregoTableModifier extends AbstractCardModifier {

    boolean reduceCost;

    public AlteregoTableModifier(boolean reduceCost) {
        this.reduceCost = reduceCost;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.selfRetain = true;
    }

    @Override
    public void onRetained(AbstractCard card) {
        if (reduceCost && card.cost > -1) {
            card.updateCost(-1);
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (reduceCost) {
            return "alterego_mod:Retain+" + ". NL " + rawDescription;
        }
        else {
            return "Retain" + ". NL " + rawDescription;
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AlteregoTableModifier(reduceCost);
    }
}
