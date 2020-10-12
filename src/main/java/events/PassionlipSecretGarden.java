package events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.WarpedTongs;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import relics.FleetingLoveRelic;
import relics.SomeoneToFightForRelic;

import java.util.Iterator;

public class PassionlipSecretGarden extends AbstractImageEvent {

    //This isn't technically needed but it becomes useful later
    public static final String ID = "alterego_mod:SecretGarden";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;
    private int screenNum = 0;

    public PassionlipSecretGarden(){
        super(NAME, DESCRIPTIONS[0], "images/events/SecretGarden.png");

        this.imageEventText.setDialogOption(OPTIONS[0], new FleetingLoveRelic());
        this.imageEventText.setDialogOption(OPTIONS[1], new SomeoneToFightForRelic());
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch(this.screenNum) {
            case 0:
                switch(buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new FleetingLoveRelic());
                        logMetricObtainRelic(NAME, "Choice 1", new FleetingLoveRelic());
                        break;
                    case 1:
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), new SomeoneToFightForRelic());
                        logMetricObtainRelic(NAME, "Choice 2", new SomeoneToFightForRelic());
                        break;
                    default:
                        break;
                }
                this.screenNum = 1;
                this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                this.imageEventText.clearRemainingOptions();
            default:
                this.openMap();
        }
    }
}
