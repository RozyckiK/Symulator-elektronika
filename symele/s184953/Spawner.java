package symele.s184953;

public class Spawner {

    private Handler handler;
    private Game game;
    private ResLoader loader;

    private Hud hud;

    private boolean alreadySpawned = false;

    public Spawner(Handler handler, Game game, ResLoader loader, Hud hud){
        this.handler = handler;
        this.game = game;
        this.loader = loader;
        this.hud = hud;
    }

    public void tick(){
        if(game.gameState == Game.STATE.Game && !alreadySpawned){
            handler.addObject(new Solder(378, 552, ID.Solder,128,48, loader, handler, hud));
            handler.addObject(new SolderingIron(200, 100, ID.SolderingIron, 2*48,2*128,loader.solderingIron));
            alreadySpawned = true;
        }

        if(game.gameState == Game.STATE.GameFinalResult && alreadySpawned){
            handler.clearAllObjects();
            alreadySpawned = false;
        }

        if(game.gameState == Game.STATE.Menu && alreadySpawned){
            handler.clearAllObjects();
            alreadySpawned = false;
        }


    }
}
