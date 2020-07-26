package alterego_mod;

import basemod.BaseMod;
import basemod.interfaces.*;
import cards.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import relics.MasochisticConstitutionRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

import static alterego_mod.AbstractCardEnum.Passionlip_Purple;
import static basemod.BaseMod.addColor;

@SpireInitializer
public class AlteregoMod implements EditCharactersSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditCardsSubscriber,
        EditKeywordsSubscriber {

    public static final Logger logger = LogManager.getLogger(AlteregoMod.class.getName());

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
        BaseMod.loadCustomStringsFile(StanceStrings.class, "localization/eng/StanceStrings.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "localization/eng/PowerStrings.json");
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
        BaseMod.addCard(new PassionlipChallengeComplete());
        BaseMod.addCard(new PassionlipComplex());
        BaseMod.addCard(new PassionlipLift());
        BaseMod.addCard(new PassionlipBiFurious());
        BaseMod.addCard(new Passionlip1TonWallop());
        BaseMod.addCard(new PassionlipMirror());
        BaseMod.addCard(new PassionlipSakuraPetals());
        BaseMod.addCard(new PassionlipBigMeatyClaws());
        BaseMod.addCard(new PassionlipClassAdvantage());
        BaseMod.addCard(new PassionlipPainIsPleasure());
        BaseMod.addCard(new PassionlipIdEs());
        BaseMod.addCard(new PassionlipClones());
        BaseMod.addCard(new PassionlipRidiculouslyBig());
        BaseMod.addCard(new PassionlipClap());
        BaseMod.addCard(new PassionlipCushion());
        BaseMod.addCard(new PassionlipSkirt());
        BaseMod.addCard(new PassionlipDeftHands());
        BaseMod.addCard(new PassionlipDustData());
        BaseMod.addCard(new PassionlipBrynhildrsRune());
        BaseMod.addCard(new PassionlipBrynhildrRomantia());
        BaseMod.addCard(new PassionlipPerceptionDisorder());
        BaseMod.addCard(new PassionlipPunishment());
        BaseMod.addCard(new PassionlipBeGentle());
        BaseMod.addCard(new PassionlipSchoolgirl());
        BaseMod.addCard(new PassionlipPurpleHair());
        BaseMod.addCard(new PassionlipTantrum());
        BaseMod.addCard(new PassionlipValkyrie());
        BaseMod.addCard(new PassionlipGIGAMILK());
        BaseMod.addCard(new PassionlipSmother());
        BaseMod.addCard(new PassionlipStockings());
        BaseMod.addCard(new PassionlipDiscoLights());
        BaseMod.addCard(new PassionlipSakuraLabyrinth());
        BaseMod.addCard(new PassionlipGoddessCore());
        BaseMod.addCard(new PassionlipSakuraFive());
        BaseMod.addCard(new PassionlipBoobPocket());
        BaseMod.addCard(new PassionlipHighServant());
        BaseMod.addCard(new PassionlipIndependentAction());
        BaseMod.addCard(new PassionlipBounce());
        BaseMod.addCard(new PassionlipImpale());
        BaseMod.addCard(new PassionlipCollar());
        BaseMod.addCard(new PassionlipBusterCard());
        BaseMod.addCard(new PassionlipPalingenesis());
        BaseMod.addCard(new PassionlipBustOut());
        BaseMod.addCard(new PassionlipGachaTicket());
        BaseMod.addCard(new PassionlipNekomimi());
        BaseMod.addCard(new PassionlipLingerie());
        BaseMod.addCard(new PassionlipBelle());
        BaseMod.addCard(new PassionlipBloomers());
        BaseMod.addCard(new PassionlipBunnysuit());
        BaseMod.addCard(new PassionlipTrashNCrash());
        BaseMod.addCard(new PassionlipDestructionOfProperty());
        BaseMod.addCard(new PassionlipMaintainEyeContact());
        BaseMod.addCard(new PassionlipValentines());
        BaseMod.addCard(new PassionlipBreastValley());
        BaseMod.addCard(new PassionlipAlter());
        BaseMod.addCard(new PassionlipRake());
        BaseMod.addCard(new PassionlipVirginLaserPalladion());
        BaseMod.addCard(new PassionlipInsideBaseball());
        BaseMod.addCard(new PassionlipSideboobHour());
        BaseMod.addCard(new PassionlipWeigh());
        BaseMod.addCard(new PassionlipSanta());
        BaseMod.addCard(new PassionlipSupport());
        BaseMod.addCard(new PassionlipRip());
        BaseMod.addCard(new PassionlipCrossover());
        BaseMod.addCard(new PassionlipTable());
        BaseMod.addCard(new PassionlipSuperDuperMasochism());
        BaseMod.addCard(new PassionlipAntipathy());
        BaseMod.addCard(new PassionlipSTier());
        BaseMod.addCard(new PassionlipExercise());
        BaseMod.addCard(new PassionlipUndulate());
        BaseMod.addCard(new PassionlipAlterEgo());
        BaseMod.addCard(new PassionlipVow());
        BaseMod.addCard(new PassionlipRingBreak());

        //UnlockTracker.unlockCard("alterego_mod:RocketPunch");
    }

    @Override
    public void receiveEditKeywords() {
        logger.info("Setting up Alterego keywords.");
        Gson gson = new Gson();
        String json = Gdx.files.internal("localization/eng/KeywordStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                logger.info("Loading keyword : " + keyword);
                BaseMod.addKeyword("alterego_mod:", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
}