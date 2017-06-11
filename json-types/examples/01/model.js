
template(   "file://./abc",
    each( ".Inputs[0:1]" )
    each(  )
    create( {
            "name": text( "pattern" ),
            "born": date( "DD.mm.YYYY" ),
            "hp": number({
                min: 0,
                max: 1,
                persitedAs: "integer"
            }),
            "active": true,
            "bignr": "111111111111111.11",

            "address": {
                "stree": "Musterstrasse"
            }
        }
    )
      .transform()
      .validate()
      .output()
);