package symele.s184953;

public class Spawner {

    private Handler handler;
    private Game game;

    private boolean alreadySpawned = false;

    public Spawner(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
    }

    public void tick(){
        if(game.gameState == Game.STATE.Game1 && !alreadySpawned){
            handler.addObject(new Player(100, 100, ID.Player , 32 ,32));
            handler.addObject(new SolderingIron(200, 100, ID.SolderingIron, 32,64));
            alreadySpawned = true;
        }

        if(game.gameState == Game.STATE.Menu && alreadySpawned){
            handler.clearAllObjects();
            alreadySpawned = false;
        }
    }
}
