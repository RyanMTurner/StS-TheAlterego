package alterego_mod;

import java.util.ArrayList;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import cards.PassionlipBigMeatyClaws;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

public class TheAlteregoCharacter extends CustomPlayer {
    public static final int ENERGY_PER_TURN = 3; // how much energy you get every turn
    public static final String MY_CHARACTER_SHOULDER_2 = "images/shoulder.png"; // campfire pose
    public static final String MY_CHARACTER_SHOULDER_1 = "images/shoulder.png"; // another campfire pose
    public static final String MY_CHARACTER_CORPSE = "images/corpse.png"; // dead corpse
    public static final String MY_CHARACTER_SKELETON_ATLAS = "images/skeleton/skeleton.atlas"; // spine animation atlas
    public static final String MY_CHARACTER_SKELETON_JSON = "images/skeleton/skeleton.json"; // spine animation json

    public static final String[] ENERGY_ORB_LAYER_TEXTURES = {
            "images/energy_orb/layer1.png",
            "images/energy_orb/layer2.png",
            "images/energy_orb/layer3.png",
            "images/energy_orb/layer4.png",
            "images/energy_orb/layer5.png",
            "images/energy_orb/layer6.png",
            "images/energy_orb/layer1d.png",
            "images/energy_orb/layer2d.png",
            "images/energy_orb/layer3d.png",
            "images/energy_orb/layer4d.png",
            "images/energy_orb/layer5d.png"};

    public TheAlteregoCharacter (String name) {
        super(name,
                TheAlteregoEnum.The_Alterego,
                ENERGY_ORB_LAYER_TEXTURES,
                "images/energy_orb/vfx.png",
                (float[]) null,
                new SpriterAnimation("images/idle_animation/idleanimation.scml"));

        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(null, MY_CHARACTER_SHOULDER_2, // required call to load textures and setup energy/loadout
                MY_CHARACTER_SHOULDER_1,
                MY_CHARACTER_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        //loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1.0F); // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines

        //AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        //e.setTime(e.getEndTime() * MathUtils.random());
    }

    // The class name as it appears next to your player name in-game
    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return "The Alterego";
    }

    // Should return the card color enum to be associated with your character.
    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.Passionlip_Purple;
    }

    // Should return a Color object to be used to color the miniature card images in run history.
    @Override
    public Color getCardRenderColor() {
        return new Color(.5f, .38f, .69f, 1);
    }

    // Should return a color object to be used to color the trail of moving cards
    @Override
    public Color getCardTrailColor() {
        return new Color(.5f, .38f, .69f, 1);
    }

    //Which card should be obtainable from the Match and Keep event?
    @Override
    public AbstractCard getStartCardForEvent() {
        return new PassionlipBigMeatyClaws();
    } //TODO: Make this a random one of the stance cards?

    // Should return how much HP your maximum HP reduces by when starting a run at
    // Ascension 14 or higher. (ironclad loses 5, defect and silent lose 4 hp respectively)
    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    // Should return a BitmapFont object that you can use to customize how your
    // energy is displayed from within the energy orb.
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontPurple;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        //CardCrawlGame.sound.playA("ATTACK_FIRE", 0.8f);
        //CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {
        return "The Alterego";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheAlteregoCharacter(name);
    }

    @Override
    public String getSpireHeartText() {
        return "NL You lash out with your claws...";
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.PURPLE;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[1];
    }

    @Override
    public String getPortraitImageName() {
        return null;
    }

    @Override
    public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
        ArrayList<String> retVal = new ArrayList<>();

        retVal.add("alterego_mod:Strike");
        retVal.add("alterego_mod:Strike");
        retVal.add("alterego_mod:Strike");
        retVal.add("alterego_mod:Strike");
        retVal.add("alterego_mod:Defend");
        retVal.add("alterego_mod:Defend");
        retVal.add("alterego_mod:Defend");
        retVal.add("alterego_mod:Defend");
        retVal.add("alterego_mod:Impale");
        retVal.add("alterego_mod:BigMeatyClaws");
        retVal.add("alterego_mod:BrynhildrsRune");

        retVal.add("alterego_mod:BreastValley");
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() { // starting relics - also simple
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("alterego_mod:MasochisticConstitution");
        //retVal.add(com.megacrit.cardcrawl.relics.FrozenEye.ID);
        UnlockTracker.markRelicAsSeen("alterego_mod:MasochisticConstitution");
        return retVal;
    }

    public static final int STARTING_HP = 70;
    public static final int MAX_HP = 70;
    public static final int STARTING_GOLD = 100;
    public static final int HAND_SIZE = 5;
    public static final int ORB_SLOTS = 0;

    @Override
    public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo("The Alterego", "The Alterego of love and hate, Passionlip!",
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, HAND_SIZE,
                this, getStartingRelics(), getStartingDeck(), false);
    }

}