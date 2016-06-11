# Calculation
Java Swing の電卓プログラム
(今更だけど名前はCalculatorとかの方が良かったかもしれない)

()付きの四則計算が行える電卓です．小数点も使えます．double型なので誤差が生じますが．

二分木構造を用いて計算処理を行います．
GUIのキーボードで受け取ったInputStreamをJavaCCで生成したパーサで解析して二分木を生成し，計算して表示します．

以下軽い説明
# calc.jj
JavaCCの構文解析用ファイルです．
文字入力で逐次呼び出され，Node型の二分木を生成します．
＋と－，＊と/は実質的に同じような計算なので，それぞれ「または」という形で処理しています．
不正入力はここでは考慮してないのでExceptionでどうにかしてください．

# Keyboard.java
GUIキーボードのJPanelです．
だいたい電卓と同じ文字の配置だと思います．
入力はInputStreamとなって受け渡しできます．

# Node.java
二分木のクラスです．
抽象クラスNodeを継承した2つのクラスNumとOpで構成されます．
Numは数，Opは演算子です．
基本的にOpがノードの最上位であるため，計算はOpクラスで行っています．
Perserでの()の検出により，ノードの配置(深度)を決定し，そこで計算の順序が左右されます．
階層が深ければ深いほど先に計算されます．
メソッドCalc()で計算結果をdouble型で返し，メソッドdisplayNodeで式を文字列で返します．Perserクラスでの表示に使ってます．

# Main.java
Mainです．SwingでGUIを構成しています．
計算結果を表示するJlabelを継承したMyLabelクラスとウィンドウ表示のためのJFrameクラスを継承したMyLabelクラスも存在してます．
ループの中で計算を行い，なんか不正入力されたら例外処理で「error!!」と表示します．
正常な入力であれば最終的に「(1+1) = 2」みたいな形で表示されます．

