package cavegen;

public class Main
{

	public static void main(String args[])
	{
		//set initial parameters
		int width = 80;
		int height = 20;
		
		//generate & print map
		int[][] map = generateCave(width, height, 5);
		printMap(map, width, height);
		
	}
	
	static int[][] generateCave(int w, int h, int refineSteps)
	{
		//create map
		int map[][] = new int[w][h];
		
		//loop through map, setting initial values
		for(int i = 0; i < w; i++)
		{
			for(int j = 0; j < h; j++)
			{
				map[i][j] = Math.round((float)Math.random()); //random noise
			}
		}
		
		//loop through map, refining
		for(int k = 0; k < refineSteps; k++)
		{
			for(int i = 0; i < w; i++)
			{
				for(int j = 0; j < h; j++)
				{
					
					int wallsCt = 0;
					//check surrounding tiles
					for(int m = -1; m <= 1; m++)
					{
						for(int n = -1; n <= 1; n++)
						{
							if(isSafe(i + m, j + n, w, h) && map[i + m][j + n] == 1)
								wallsCt++;
						}
					}
					
					//set floor if not surrounded by enough walls, and vice versa
					if(wallsCt >= 5)
						map[i][j] = 1;
					else
						map[i][j] = 0;
					
				}
			}
		}
		
		//return final map
		return map;
		
	}
	
	static boolean isSafe(int x, int y, int w, int h)
	{
		return (x >= 0 && x < w && y >= 0 && y < h);
	}
	
	static void printMap(int map[][], int w, int h)
	{
		//create empty output
		String output = "";
		
		//loop through map, adding to output
		for(int j = 0; j < h; j++)
		{
			for(int i = 0; i < w; i++)
			{
				//append characters based on map values
				if(map[i][j] == 0)
					output += ".";
				else
					output += "X";
			}
			//newline
			output += "\n";
		}
		
		//print output
		System.out.println(output);
	}
	
}
