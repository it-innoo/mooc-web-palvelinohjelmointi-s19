/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoreservice;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jukka
 */
@RestController
public class ScoreController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping("/games/{name}/scores")
    public List<Score> getScores(@PathVariable String name) {
        Game game = gameRepository.findByName(name);
        return scoreRepository.findByGame(game);
    }

    @GetMapping("/games/{name}/scores/{id}")
    public Score getScore(@PathVariable String name, @PathVariable Long id) {
        Game game = gameRepository.findByName(name);
        return scoreRepository.findByGameAndId(game, id);
    }

    @PostMapping("/games/{name}/scores")
    public Score addScore(@RequestBody Score score, @PathVariable String name) {
        Game game = gameRepository.findByName(name);
        score.setGame(game);

        return scoreRepository.save(score);
    }

    @DeleteMapping("/games/{name}/scores/{id}")
    public Score deleteScore(@PathVariable String name, @PathVariable Long id) {
        Game game = gameRepository.findByName(name);
        Score score = scoreRepository.findByGameAndId(game, id);
        scoreRepository.delete(score);

        return score;
    }
}
