# 下記のコマンドでコンテナを立ち上げる
# $ docker build -t simple-task-manager .
# $ docker run -it --rm -v "$(pwd)/data":/app/data simple-task-manager

# ベースイメージの指定（例: OpenJDK 17のスリム版）
FROM openjdk:17-slim

# 環境変数でタスクファイルパスを指定
ENV TASK_FILE=/data/tasks.txt

# 作業ディレクトリを作成
WORKDIR /app

# ローカルのソースコードをコンテナにコピー
# ここでは、シンプルなJavaプロジェクト構成を想定（srcフォルダ等）
COPY . /app

# ビルド／出力ディレクトリの作成
RUN mkdir -p out /data

# コンパイル（必要な場合）
# ここでソースコードをjavacでコンパイルしておく
# 例: SimpleTaskManager.java がエントリーポイントの場合
RUN mkdir -p out
RUN javac -d out src/main/java/com/example/**/*.java

# 永続化用ボリュームを定義
VOLUME ["/data"]

# エントリーポイントの設定
# ビルド済みのクラスファイルを実行（例: mainクラスがcom.example.cli.TaskManagerCLIの場合）
CMD ["java", "-cp", "out", "com.example.cli.TaskManagerCLI"]
