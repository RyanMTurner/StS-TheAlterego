package alterego_mod;

import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import cards.*;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import relics.MasochisticConstitutionRelic;


import static alterego_mod.AbstractCardEnum.Passionlip_Purple;
import static basemod.BaseMod.addColor;

@SpireInitializer
public class AlteregoMod implements EditCharactersSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditCardsSubscriber {

    public AlteregoMod() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new AlteregoMod();
        BaseMod.addColor(Passionlip_Purple,
                new Color(.5f, .38f, .69f, 1),
                "images/512/bg_attack.png",
                "images/512/bg_skill.png",
                "images/512/bg_power.png",
                "images/512/bg_orb.png",
                "images/1024/bg_attack.png",
                "images/1024/bg_skill.png",
                "images/1024/bg_power.png",
                "images/1024/energy_orb.png",
                "images/SakuraChip.png");
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheAlteregoCharacter(CardCrawlGame.playerName),
                "images/char_select_button.png",
                "images/char_select_bg.png",
                TheAlteregoEnum.The_Alterego);
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(RelicStrings.class, "localization/eng/RelicStrings.json");
        BaseMod.loadCustomStringsFile(CardStrings.class, "localization/eng/CardStrings.json");
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new MasochisticConstitutionRelic(), Passionlip_Purple);
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addCard(new PassionlipStrike());
        BaseMod.addCard(new PassionlipDefend());
        BaseMod.addCard(new PassionlipCooking());
        BaseMod.addCard(new PassionlipBirthday());
        BaseMod.addCard(new PassionlipMaid());
        BaseMod.addCard(new PassionlipRocketPunch());
        BaseMod.addCard(new PassionlipVenus());
        BaseMod.addCard(new PassionlipSwimsuit());
        BaseMod.addCard(new PassionlipJiggle());
        BaseMod.addCard(new PassionlipMagicResistance());
        BaseMod.addCard(new PassionlipPet());
        BaseMod.addCard(new PassionlipBelts());
        BaseMod.addCard(new PassionlipPresenceConcealment());

        //UnlockTracker.unlockCard("alterego_mod:RocketPunch");
    }
}