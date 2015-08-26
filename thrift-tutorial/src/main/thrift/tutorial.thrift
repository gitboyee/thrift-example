include "shared.thrift"

namespace java thrift.tutorial

typedef i32 MyInteger
const i32 INT32CONSTANT = 9853
const map<string,string> MAPCONSTANT = {'hello':'world'}

enum Operation{
    ADD =1,
    SUBTRACT = 2,
    MULTYPLY = 3,
    DIVEDE = 4
}
struct Work {
1:i32 num1 = 0,
2:i32 num2,
3:Operation op,
4:optional string comment,
 }
 
 /**
 *structs can be exceptions
 */
 exception InvalidOperation{
    1: i32 whatOp,
    2: string why
 }
 
 service Calculator extends shared.SharedService {
    void ping(),
    i32 add(1: i32 num1,2: i32 num2),
    i32 calculate(1: i32 logid, 2: Work w) throws (1: InvalidOperation ouch),
    
    oneway void zip()
 }
