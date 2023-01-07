package symele.s184953;

/**
 * Klasa odpowiedzialna za tworzenie obiektów w grze
 * @author Kacper Różycki
 */
public class Spawner {

    /**
     * Deklaracja instancji handlera
     */
    private Handler handler;

    /**
     * Deklaracja instancji gry
     */
    private Game game;
    /**
     * Deklaracja instancji loadera
     */
    private ResLoader loader;

    /**
     * Deklaracja instancji hud
     */
    private Hud hud;

    /**
     * boolan zapisujący czy obiekty są aktualnie wygenerowane
     */
    private boolean alreadySpawned = false;

    /**
     * Konstruktor klasy spawnera
     * @param handler przekazuje handlera
     * @param game przekazuje gre
     * @param loader przekazuje loadera
     * @param hud przekazuje hud
     */
    public Spawner(Handler handler, Game game, ResLoader loader, Hud hud){
        this.handler = handler;
        this.game = game;
        this.loader = loader;
        this.hud = hud;
    }

    /**
     * Metoda odświeżająca klase spawner
     */
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
