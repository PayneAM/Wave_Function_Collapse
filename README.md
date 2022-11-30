# WaveFunctionCollapse
Tilemap generation

Desc -
Generate A tile map using wave function collapse. Future features would be a faster algorithm than the one implemented, more options outside of Wang Tiles, and a slow run feature allowing users to step through generation.

RUN PROCEDURE -
1. Clone the repository and make sure file paths match the GIT
2. Open up the code editor of your choice in this projects case IntelliJ by JetBrains was used and open the code folder from the cloned GIT
3. Open and Run Window.Java Found in Code\src\Main
4. Upon running you will be presented with a small console with 3 scroll bars a drop-down and a button. The scroll bars correspond with the Tilemap settings size of the map (Width x Height) and the size of the image. The drop-down has tile maps that already exist in the Tiles file test tiles are the fastest way to verify the program is in order. Generate will load a tile map with your selected inputs(Note: upon clicking generate previous Tile map will disappear).


ERROR PROCEDURE -
1. In case of error run test cases found in Code\src\Test
	a. Tile Test Checks compatibility function if the input tile map is generating an incorrect map and the sides of the tile are correctly delivered please send a request to fix the error. 
	b. Tile Dict Test checks if all the test case tiles are correctly loading and match the expected results 
	i. in case of null failure check file paths 
	ii. in the case of  length failure check fail paths
	iii. all other test cases will only fail due to function failure in this case submit a request to fix

-Credits
Inspirations -
	https://github.com/mxgmn/WaveFunctionCollapse.git
	https://github.com/CodingTrain/Wave-Function-Collapse.git
Tile Image Origins -
	http://cr31.co.uk/stagecast/wang/intro.html

MIT License

Copyright (c) 2022 Aleksey Payne

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES, OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.