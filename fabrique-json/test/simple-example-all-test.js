/**
 * Created by asiebert on 08.06.2017.
 */

var assert = require('chai').assert;

var deps = require('../impl/dependencies');


describe("Process examples", function () {

    describe("process - example", function () {

        it("handle select.each.",
            function (done) {
                deps(function () {

                    var input = {
                        "Invoices": [
                            {
                                value: 10
                            },
                            {
                                value: 22
                            }
                        ],
                        name: "Test"
                    };

                    load(input)
                        .process(
                            select(".Invoices").each(
                                function (node) {
                                    console.log("nodes selected: ");
                                    console.log(node);

                                    assert.typeOf(node, "object", "return value should be an object");
                                }
                            ),
                            function (result) {
                                console.log("nodes selected: ");
                                console.log(result);

                                assert.typeOf(result, "array", "return value should be an array");
                                assert.lengthOf(result, 2, "nr of all selected elements");

                                done();
                            }
                        );
                })
            });


    });

});



