{	
	"id" : 0,
	"name" : "Test Slot",
	"slug" : "test_slot",
	"base" : {
		"dualStateSpin" : false,
		"numberOfLines" : 25,
		"maxCoinsPerLine" : 1,
		"defaultLineBets" : [1, 0, 0, 0, 0, 0, 0, 0, 0, 0],
		"maxLineBets" : [1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
		"symbols" : [
			{ "id" : 0, "code" : "WILD", "wild" : true},
			{"id" : 1, "code" : "SYMBOL_1"},
			{"id" : 2, "code" : "SYMBOL_2"},
			{"id" : 3, "code" : "SYMBOL_3"},
			{"id" : 4, "code" : "SYMBOL_4"},
			{"id" : 5, "code" : "SYMBOL_5"},
			{"id" : 6, "code" : "SYMBOL_6"},
			{"id" : 7, "code" : "SYMBOL_7"},
			{"id" : 8, "code" : "SYMBOL_8"},
			{"id" : 9, "code" : "SYMBOL_0"},
			{"id" : 10, "code" : "SCATTER"}
		],
		"defaultReels" : [
			[3, 9, 7],
			[2, 6, 1],
			[7, 4, 0],
			[3, 9, 2],
			[5, 7, 4]
		],
		"reels" : {
			"spinner" : "RandomSpinner",
			"SymbolsPerReel" : [3, 3, 3, 3, 3],
			"Reels" : [
			    {
					"index" : 0,
					"symbols" : [0,0,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,5,5,6,6,7,7,7,7,7,7,7,7,7,7,7,7,7,8,8,8,8,8,8,8,8,8,8,8,8,8,9,9,9,9,9,9,10,10]
				},
				 {
					"index" : 1,
					"symbols" : [0,0,0,1,1,1,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4,5,5,6,6,6,6,6,6,6,6,6,6,6,7,7,7,7,7,7,8,8,8,8,8,8,8,8,8,8,8,8,8,9,9,9,9,9,9,9,9,9,9,9,9,9,10,10]
				},
				{
					"index" : 2,
					"symbols" : [0,0,1,1,1,1,1,1,2,2,2,2,2,2,3,3,3,3,3,3,4,4,5,5,5,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,6,7,7,7,7,7,7,7,7,7,7,7,7,7,8,8,8,8,8,8,9,9,9,9,9,9,9,9,9,9,9,9,9,9,10,10]
				},
				{
					"index" : 3,
					"symbols" : [0,0,1,1,1,1,2,2,2,2,3,4,4,4,5,5,5,5,6,6,6,6,6,7,7,7,7,7,7,8,8,8,8,8,9,9,9,9,9,9,10]
				},
				{
					"index" : 4,
					"symbols" : [0,1,2,2,2,3,3,3,3,3,3,4,4,4,4,5,5,5,5,5,6,6,6,6,6,7,7,7,7,7,8,8,8,8,8,8,9,9,9,9,9,9,10]
				}	
			]
		},
		"models" : [
			{	"type" : "slotModel",
				"lineHelper" : [
					[1,1,1,1,1],
					[0,0,0,0,0],
					[2,2,2,2,2],
					[0,1,2,1,0],
					[2,1,0,1,2],
					[1,2,1,2,1],
					[1,0,1,0,1],
					[2,1,2,1,2],
					[0,1,0,1,0],
					[0,0,1,2,2],
					[2,2,1,0,0],
					[0,1,1,1,2],
					[2,1,1,1,0],
					[0,2,2,2,0],
					[2,0,0,0,2],
					[1,0,0,0,1],
					[1,2,2,2,1],
					[0,2,0,2,0],
					[2,0,2,0,2],
					[0,2,1,2,0],
					[2,0,1,0,2],
					[0,0,2,0,0],
					[2,2,0,2,2],
					[1,1,0,1,2],
					[1,1,2,1,0]
				],
				"payoutCombos" : [
					{ "id" : 1, "symbol" : 1, "occurrence" : 5, "win" : 500},
					{ "id" : 2, "symbol" : 1, "occurrence" : 4, "win" : 30},
					{ "id" : 3, "symbol" : 1, "occurrence" : 3, "win" : 10},
					{ "id" : 4, "symbol" : 1, "occurrence" : 2, "win" : 2},
					{ "id" : 5, "symbol" : 2, "occurrence" : 5, "win" : 250},
					{ "id" : 6, "symbol" : 2, "occurrence" : 4, "win" : 30},
					{ "id" : 7, "symbol" : 2, "occurrence" : 3, "win" : 10},
					{ "id" : 8, "symbol" : 2, "occurrence" : 2, "win" : 2},
					{ "id" : 9, "symbol" : 3, "occurrence" : 5, "win" : 250},
					{ "id" : 10, "symbol" : 3, "occurrence" : 4, "win" : 30},
					{ "id" : 11, "symbol" : 3, "occurrence" : 3, "win" : 10},
					{ "id" : 12, "symbol" : 3, "occurrence" : 2, "win" : 2},
					{ "id" : 13, "symbol" : 4, "occurrence" : 5, "win" : 100},
					{ "id" : 14, "symbol" : 4, "occurrence" : 4, "win" : 20},
					{ "id" : 15, "symbol" : 4, "occurrence" : 3, "win" : 10},
					{ "id" : 16, "symbol" : 5, "occurrence" : 5, "win" : 75},
					{ "id" : 17, "symbol" : 5, "occurrence" : 4, "win" : 20},
					{ "id" : 18, "symbol" : 5, "occurrence" : 3, "win" : 10},
					{ "id" : 19, "symbol" : 6, "occurrence" : 5, "win" : 50},
					{ "id" : 20, "symbol" : 6, "occurrence" : 4, "win" : 20},
					{ "id" : 21, "symbol" : 6, "occurrence" : 3, "win" : 10},
					{ "id" : 22, "symbol" : 7, "occurrence" : 5, "win" : 30},
					{ "id" : 23, "symbol" : 7, "occurrence" : 4, "win" : 10},
					{ "id" : 24, "symbol" : 7, "occurrence" : 3, "win" : 5},
					{ "id" : 25, "symbol" : 8, "occurrence" : 5, "win" : 30},
					{ "id" : 26, "symbol" : 8, "occurrence" : 4, "win" : 10},
					{ "id" : 27, "symbol" : 8, "occurrence" : 3, "win" : 5},
					{ "id" : 28, "symbol" : 9, "occurrence" : 5, "win" : 30},
					{ "id" : 29, "symbol" : 9, "occurrence" : 4, "win" : 10},
					{ "id" : 30, "symbol" : 9, "occurrence" : 3, "win" : 5}
				]

			},
			{	"type" : "scatterModel",
				"symbol" : 10,
				"wins" : [0, 0, 0, 4, 8, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20]
			}
		]
	}
 
}