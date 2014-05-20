package net.humanity_game.server.packets.core;

import com.google.common.base.Preconditions;
import net.humanity_game.server.cards.card.BlackCard;
import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.packets.Packet;

import java.util.*;

/**
 * User: Jack Date: 5/20/2014 Time: 4:14 PM
 */
public class Packet14SubmissionChoices extends Packet {

    private final BlackCard blackCard;
    private Set<Submission> options;

    public Packet14SubmissionChoices(UUID czar, BlackCard card, HashMap<UUID, Collection<WhiteCard>> subs) {
        super(czar);
        blackCard = Preconditions.checkNotNull(card, "black card");
        Preconditions.checkNotNull(subs, "submissions");
        Preconditions.checkState(subs.size() > 1, "subs size");
        options = new HashSet<Submission>(subs.size());
        for (Map.Entry<UUID, Collection<WhiteCard>> entry : subs.entrySet()) {
            Preconditions.checkNotNull(entry.getValue());
            options.add(new Submission(entry.getKey(), entry.getValue()));
        }
    }

    /**
     * This is used because it is more efficient, which, looking back on it, isn't a huge deal.
     */
    public class Submission {
        UUID uuid;
        Collection<WhiteCard> whiteCards;

        protected Submission(UUID uuid, WhiteCard card) {
            this(uuid, Collections.singleton(card));
        }

        protected Submission(UUID uuid, Collection<WhiteCard> cards) {
            this.uuid = Preconditions.checkNotNull(uuid);
            cards.removeAll(Collections.singleton(null));
            this.whiteCards = cards;
        }
    }
}
