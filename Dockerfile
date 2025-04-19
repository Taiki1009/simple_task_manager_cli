# 下記のコマンドでコンテナを立ち上げる
# $ docker build -t simple-task-manager .
# $ docker run -it simple-task-manager

# ベースイメージの指定（例: OpenJDK 17のスリム版）
FROM openjdk:17-slim

# 作業ディレクトリを作成
WORKDIR /app

# ローカルのソースコードをコンテナにコピー
# ここでは、シンプルなJavaプロジェクト構成を想定（srcフォルダ等）
COPY . /app

# コンパイル（必要な場合）
# ここでソースコードをjavacでコンパイルしておく
# 例: SimpleTaskManager.java がエントリーポイントの場合
RUN javac -d out src/main/java/com/example/**/*.java

# エントリーポイントの設定
# ビルド済みのクラスファイルを実行（例: mainクラスがcom.example.cli.TaskManagerCLIの場合）
CMD ["java", "-cp", "out", "com.example.cli.TaskManagerCLI"]
