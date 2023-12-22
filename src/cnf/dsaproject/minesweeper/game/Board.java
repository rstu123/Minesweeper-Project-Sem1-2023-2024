package cnf.dsaproject.minesweeper.game;

import java.awt.Graphics;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import cnf.dsaproject.minesweeper.game.states.*;
import cnf.dsaproject.minesweeper.gfx.Assets;

public class Board {
    private int N;
	private int NMines;
	private int NCovered;
	private GameStates Gamestate;
	
	// save data in separate arrays, instead of an array of objects
	// this increases CPU-Register cache hit so can run faster
	private boolean[][] isMine;
	private int[][] mineCnt;
	private CellStates[][] states;

	// used in putMines() and bfs()
	private final int[] di = new int[] { -1, -1, -1, 0, 1, 1, 1, 0 };
	private final int[] dj = new int[] { -1, 0, 1, 1, 1, 0, -1, -1 };

	private final CellStates[] uncoveredStates = new CellStates[] { 
			CellStates.UNC0, CellStates.UNC1, CellStates.UNC2, CellStates.UNC3, 
			CellStates.UNC4, CellStates.UNC5, CellStates.UNC6, CellStates.UNC7, CellStates.UNC8 
	};
	public Board(int N, int NMines) {
		// parameters should have be checked before here
		// if, somehow, they are still invalid, overwrite them with defaults
		if (N < 10 || N > 1000 || NMines < 1 || NMines > N * N) {
			N = 30;
			NMines = 100;
		}

		this.N = N;
		this.NCovered = N * N;
		this.NMines = NMines;

		isMine = new boolean[N][N];
		mineCnt = new int[N + 2][N + 2];
		states = new CellStates[N][N];

		putMines();

		for (int i = 0; i < N; i++)
			Arrays.fill(states[i], CellStates.COVERED);

		gameState = GameStates.ONGOING;
	}

	// randomly place mines in the board
	// and update "count of mines" of neighboring cells
}
