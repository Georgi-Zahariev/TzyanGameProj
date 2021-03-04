class Tzyan {
	private int a, b;
	private byte[][] m = new byte[100][100];

	private void cleanUp(int x, int y) {
		m[x][y] = -1;
		for (int i = x + 1; i < 100; i++){
			if (m[i][y] == 0){
				m[i][y] = 1;
			}
		}
		for (int i = y + 1; i < 100; i++){
			if (m[x][i] == 0){
				m[x][i] = 2;
			}
		}
		for (int i = 1; x + i < 100 && y + i < 100; i++){
			if (m[x + i][y + i] == 0){
				m[x + i][y + i]=3;
			}
		}
	}

	private void init(){
		for ( int i=0;i<100 ;i++){
			for ( int j=0;j<100 ;j++) m[i][j]=0;
			}
				m[0][0]=-1;
				cleanUp(0,0);
			
		for(int x=1;x<100;x++){
			for(int y =0; y<100;y++){
				if(m[x][y]==0){
					cleanUp(x,y);
					cleanUp(y,x);
					break;
				}
			}
		}
	}
	public void showM( int n){
		for(int x=0;x<n;x++){
			for(int y =0; y<n;y++){
				if(m[x][y]==-1){
					System.out.print("X ");
				}
				else{
					System.out.print(m[x][y] + " ");
				}
			}
			System.out.println();
		}
	}
	public void showPos(){
		for(int x=0;x<100;x++){
			for(int y =x; y<100;y++){
				if(m[x][y]==-1){
					System.out.print("("+x+", "+y+")");
				}
			}
		}
		System.out.println();
	}

	public Tzyan(int a, int b) {
		this.a = a;
		this.b = b;
		init();
	}

	public void doMove(int kind, int cnt) {
		switch (kind) {
		case 1:
			a -= cnt;
			break;
		case 2:
			b -= cnt;
			break;
		case 3: {
			a -= cnt;
			b -= cnt;
		}
		}
	}

	public void undoMove(int kind, int cnt) { // Vyzstanovqvqme sled hoda
		doMove(kind, -cnt);
	}

	public int play(int player) {
		int q;
		if (a == 0 && b == 0)
			return (player ^ 0b11); // player^3
		if (a == 0 || b == 0 || a == b)
			return player;

		for (int i = 1; i <= a; i++) {
			doMove(1, i);
			q = play(player ^ 3);
			undoMove(1, i);
			if (q == player)
				return player;
		}

		for (int i = 1; i <= b; i++) {
			doMove(2, i);
			q = play(player ^ 3);
			undoMove(2, i);
			if (q == player)
				return player;
		}

		for (int i = 1; i <= a && i <= b; i++) {
			doMove(3, i);
			q = play(player ^ 3);
			undoMove(3, i);
			if (q == player)
				return player;
		}
		return (player ^ 3);
	}
}

public class Main {

	public static void main(String[] args) {
		// for ( int i=1;i<=10 ;i++)
		// for ( int j=i+1;j<=10 ;j++){
		// Tzyan g = new Tzyan( i,j);
		// if( g.play(1)==2) System.out.println("("+ i +", "+ j + ")");
		//
		// }
		// System.out.println("Done");

		Tzyan q = new Tzyan(15,20);
		q.showM(10);
		q.showPos();
		

	}

}

