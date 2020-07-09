package cardmodifiers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.AbstractCardModifier;

public class AlteregoCloneModifier extends AbstractCardModifier {

    public AlteregoCloneModifier() {
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.selfRetain = true;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return "Retain. NL " + rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AlteregoCloneModifier();
    }
}
