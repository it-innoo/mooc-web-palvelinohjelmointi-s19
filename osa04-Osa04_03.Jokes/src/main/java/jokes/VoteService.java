package jokes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jukka Ahlgren <jukka.ahlgren at gmail.com>
 */
@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public Vote findByJokeId(Long jokeId) {
        Vote vote = voteRepository.findByJokeId(jokeId);
        if (vote == null) {
            vote = new Vote(jokeId, 0, 0);
            voteRepository.save(vote);
        }

        return vote;
    }

    @Transactional
    public void vote(Long id, String value) {
        Vote vote = voteRepository.findByJokeId(id);
        if (vote == null) {
            vote = new Vote(id, 0, 0);
        }

        if ("up".equals(value)) {
            vote.setUpVotes(vote.getUpVotes() + 1);
        } else if ("down".equals(value)) {
            vote.setDownVotes(vote.getDownVotes() + 1);
        }
        voteRepository.save(vote);
    }
}
