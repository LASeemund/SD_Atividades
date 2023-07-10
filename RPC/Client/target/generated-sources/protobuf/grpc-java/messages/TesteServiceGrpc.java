package messages;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.56.0)",
    comments = "Source: protocolo.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TesteServiceGrpc {

  private TesteServiceGrpc() {}

  public static final String SERVICE_NAME = "messages.TesteService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<messages.Protocolo.FindTitle,
      messages.Protocolo.Movie> getFindtitleMsgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findtitleMsg",
      requestType = messages.Protocolo.FindTitle.class,
      responseType = messages.Protocolo.Movie.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<messages.Protocolo.FindTitle,
      messages.Protocolo.Movie> getFindtitleMsgMethod() {
    io.grpc.MethodDescriptor<messages.Protocolo.FindTitle, messages.Protocolo.Movie> getFindtitleMsgMethod;
    if ((getFindtitleMsgMethod = TesteServiceGrpc.getFindtitleMsgMethod) == null) {
      synchronized (TesteServiceGrpc.class) {
        if ((getFindtitleMsgMethod = TesteServiceGrpc.getFindtitleMsgMethod) == null) {
          TesteServiceGrpc.getFindtitleMsgMethod = getFindtitleMsgMethod =
              io.grpc.MethodDescriptor.<messages.Protocolo.FindTitle, messages.Protocolo.Movie>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findtitleMsg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messages.Protocolo.FindTitle.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messages.Protocolo.Movie.getDefaultInstance()))
              .setSchemaDescriptor(new TesteServiceMethodDescriptorSupplier("findtitleMsg"))
              .build();
        }
      }
    }
    return getFindtitleMsgMethod;
  }

  private static volatile io.grpc.MethodDescriptor<messages.Protocolo.FindCategory,
      messages.Protocolo.FindResponse> getFindcategoryMsgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findcategoryMsg",
      requestType = messages.Protocolo.FindCategory.class,
      responseType = messages.Protocolo.FindResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<messages.Protocolo.FindCategory,
      messages.Protocolo.FindResponse> getFindcategoryMsgMethod() {
    io.grpc.MethodDescriptor<messages.Protocolo.FindCategory, messages.Protocolo.FindResponse> getFindcategoryMsgMethod;
    if ((getFindcategoryMsgMethod = TesteServiceGrpc.getFindcategoryMsgMethod) == null) {
      synchronized (TesteServiceGrpc.class) {
        if ((getFindcategoryMsgMethod = TesteServiceGrpc.getFindcategoryMsgMethod) == null) {
          TesteServiceGrpc.getFindcategoryMsgMethod = getFindcategoryMsgMethod =
              io.grpc.MethodDescriptor.<messages.Protocolo.FindCategory, messages.Protocolo.FindResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findcategoryMsg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messages.Protocolo.FindCategory.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messages.Protocolo.FindResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TesteServiceMethodDescriptorSupplier("findcategoryMsg"))
              .build();
        }
      }
    }
    return getFindcategoryMsgMethod;
  }

  private static volatile io.grpc.MethodDescriptor<messages.Protocolo.FindActor,
      messages.Protocolo.FindResponse> getFindactorMsgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "findactorMsg",
      requestType = messages.Protocolo.FindActor.class,
      responseType = messages.Protocolo.FindResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<messages.Protocolo.FindActor,
      messages.Protocolo.FindResponse> getFindactorMsgMethod() {
    io.grpc.MethodDescriptor<messages.Protocolo.FindActor, messages.Protocolo.FindResponse> getFindactorMsgMethod;
    if ((getFindactorMsgMethod = TesteServiceGrpc.getFindactorMsgMethod) == null) {
      synchronized (TesteServiceGrpc.class) {
        if ((getFindactorMsgMethod = TesteServiceGrpc.getFindactorMsgMethod) == null) {
          TesteServiceGrpc.getFindactorMsgMethod = getFindactorMsgMethod =
              io.grpc.MethodDescriptor.<messages.Protocolo.FindActor, messages.Protocolo.FindResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "findactorMsg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messages.Protocolo.FindActor.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messages.Protocolo.FindResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TesteServiceMethodDescriptorSupplier("findactorMsg"))
              .build();
        }
      }
    }
    return getFindactorMsgMethod;
  }

  private static volatile io.grpc.MethodDescriptor<messages.Protocolo.Create,
      messages.Protocolo.Movie> getCreateMsgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createMsg",
      requestType = messages.Protocolo.Create.class,
      responseType = messages.Protocolo.Movie.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<messages.Protocolo.Create,
      messages.Protocolo.Movie> getCreateMsgMethod() {
    io.grpc.MethodDescriptor<messages.Protocolo.Create, messages.Protocolo.Movie> getCreateMsgMethod;
    if ((getCreateMsgMethod = TesteServiceGrpc.getCreateMsgMethod) == null) {
      synchronized (TesteServiceGrpc.class) {
        if ((getCreateMsgMethod = TesteServiceGrpc.getCreateMsgMethod) == null) {
          TesteServiceGrpc.getCreateMsgMethod = getCreateMsgMethod =
              io.grpc.MethodDescriptor.<messages.Protocolo.Create, messages.Protocolo.Movie>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createMsg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messages.Protocolo.Create.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messages.Protocolo.Movie.getDefaultInstance()))
              .setSchemaDescriptor(new TesteServiceMethodDescriptorSupplier("createMsg"))
              .build();
        }
      }
    }
    return getCreateMsgMethod;
  }

  private static volatile io.grpc.MethodDescriptor<messages.Protocolo.Update,
      messages.Protocolo.Movie> getUpdateMsgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateMsg",
      requestType = messages.Protocolo.Update.class,
      responseType = messages.Protocolo.Movie.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<messages.Protocolo.Update,
      messages.Protocolo.Movie> getUpdateMsgMethod() {
    io.grpc.MethodDescriptor<messages.Protocolo.Update, messages.Protocolo.Movie> getUpdateMsgMethod;
    if ((getUpdateMsgMethod = TesteServiceGrpc.getUpdateMsgMethod) == null) {
      synchronized (TesteServiceGrpc.class) {
        if ((getUpdateMsgMethod = TesteServiceGrpc.getUpdateMsgMethod) == null) {
          TesteServiceGrpc.getUpdateMsgMethod = getUpdateMsgMethod =
              io.grpc.MethodDescriptor.<messages.Protocolo.Update, messages.Protocolo.Movie>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updateMsg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messages.Protocolo.Update.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messages.Protocolo.Movie.getDefaultInstance()))
              .setSchemaDescriptor(new TesteServiceMethodDescriptorSupplier("updateMsg"))
              .build();
        }
      }
    }
    return getUpdateMsgMethod;
  }

  private static volatile io.grpc.MethodDescriptor<messages.Protocolo.Delete,
      messages.Protocolo.Response> getDeleteMsgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteMsg",
      requestType = messages.Protocolo.Delete.class,
      responseType = messages.Protocolo.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<messages.Protocolo.Delete,
      messages.Protocolo.Response> getDeleteMsgMethod() {
    io.grpc.MethodDescriptor<messages.Protocolo.Delete, messages.Protocolo.Response> getDeleteMsgMethod;
    if ((getDeleteMsgMethod = TesteServiceGrpc.getDeleteMsgMethod) == null) {
      synchronized (TesteServiceGrpc.class) {
        if ((getDeleteMsgMethod = TesteServiceGrpc.getDeleteMsgMethod) == null) {
          TesteServiceGrpc.getDeleteMsgMethod = getDeleteMsgMethod =
              io.grpc.MethodDescriptor.<messages.Protocolo.Delete, messages.Protocolo.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteMsg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messages.Protocolo.Delete.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  messages.Protocolo.Response.getDefaultInstance()))
              .setSchemaDescriptor(new TesteServiceMethodDescriptorSupplier("deleteMsg"))
              .build();
        }
      }
    }
    return getDeleteMsgMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TesteServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TesteServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TesteServiceStub>() {
        @java.lang.Override
        public TesteServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TesteServiceStub(channel, callOptions);
        }
      };
    return TesteServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TesteServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TesteServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TesteServiceBlockingStub>() {
        @java.lang.Override
        public TesteServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TesteServiceBlockingStub(channel, callOptions);
        }
      };
    return TesteServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TesteServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TesteServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TesteServiceFutureStub>() {
        @java.lang.Override
        public TesteServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TesteServiceFutureStub(channel, callOptions);
        }
      };
    return TesteServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void findtitleMsg(messages.Protocolo.FindTitle request,
        io.grpc.stub.StreamObserver<messages.Protocolo.Movie> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindtitleMsgMethod(), responseObserver);
    }

    /**
     */
    default void findcategoryMsg(messages.Protocolo.FindCategory request,
        io.grpc.stub.StreamObserver<messages.Protocolo.FindResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindcategoryMsgMethod(), responseObserver);
    }

    /**
     */
    default void findactorMsg(messages.Protocolo.FindActor request,
        io.grpc.stub.StreamObserver<messages.Protocolo.FindResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindactorMsgMethod(), responseObserver);
    }

    /**
     */
    default void createMsg(messages.Protocolo.Create request,
        io.grpc.stub.StreamObserver<messages.Protocolo.Movie> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateMsgMethod(), responseObserver);
    }

    /**
     */
    default void updateMsg(messages.Protocolo.Update request,
        io.grpc.stub.StreamObserver<messages.Protocolo.Movie> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMsgMethod(), responseObserver);
    }

    /**
     */
    default void deleteMsg(messages.Protocolo.Delete request,
        io.grpc.stub.StreamObserver<messages.Protocolo.Response> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMsgMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service TesteService.
   */
  public static abstract class TesteServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return TesteServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service TesteService.
   */
  public static final class TesteServiceStub
      extends io.grpc.stub.AbstractAsyncStub<TesteServiceStub> {
    private TesteServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TesteServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TesteServiceStub(channel, callOptions);
    }

    /**
     */
    public void findtitleMsg(messages.Protocolo.FindTitle request,
        io.grpc.stub.StreamObserver<messages.Protocolo.Movie> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindtitleMsgMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findcategoryMsg(messages.Protocolo.FindCategory request,
        io.grpc.stub.StreamObserver<messages.Protocolo.FindResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindcategoryMsgMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findactorMsg(messages.Protocolo.FindActor request,
        io.grpc.stub.StreamObserver<messages.Protocolo.FindResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindactorMsgMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createMsg(messages.Protocolo.Create request,
        io.grpc.stub.StreamObserver<messages.Protocolo.Movie> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateMsgMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateMsg(messages.Protocolo.Update request,
        io.grpc.stub.StreamObserver<messages.Protocolo.Movie> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMsgMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteMsg(messages.Protocolo.Delete request,
        io.grpc.stub.StreamObserver<messages.Protocolo.Response> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMsgMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service TesteService.
   */
  public static final class TesteServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TesteServiceBlockingStub> {
    private TesteServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TesteServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TesteServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public messages.Protocolo.Movie findtitleMsg(messages.Protocolo.FindTitle request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindtitleMsgMethod(), getCallOptions(), request);
    }

    /**
     */
    public messages.Protocolo.FindResponse findcategoryMsg(messages.Protocolo.FindCategory request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindcategoryMsgMethod(), getCallOptions(), request);
    }

    /**
     */
    public messages.Protocolo.FindResponse findactorMsg(messages.Protocolo.FindActor request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindactorMsgMethod(), getCallOptions(), request);
    }

    /**
     */
    public messages.Protocolo.Movie createMsg(messages.Protocolo.Create request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateMsgMethod(), getCallOptions(), request);
    }

    /**
     */
    public messages.Protocolo.Movie updateMsg(messages.Protocolo.Update request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMsgMethod(), getCallOptions(), request);
    }

    /**
     */
    public messages.Protocolo.Response deleteMsg(messages.Protocolo.Delete request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMsgMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service TesteService.
   */
  public static final class TesteServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<TesteServiceFutureStub> {
    private TesteServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TesteServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TesteServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<messages.Protocolo.Movie> findtitleMsg(
        messages.Protocolo.FindTitle request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindtitleMsgMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<messages.Protocolo.FindResponse> findcategoryMsg(
        messages.Protocolo.FindCategory request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindcategoryMsgMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<messages.Protocolo.FindResponse> findactorMsg(
        messages.Protocolo.FindActor request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindactorMsgMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<messages.Protocolo.Movie> createMsg(
        messages.Protocolo.Create request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateMsgMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<messages.Protocolo.Movie> updateMsg(
        messages.Protocolo.Update request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMsgMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<messages.Protocolo.Response> deleteMsg(
        messages.Protocolo.Delete request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteMsgMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FINDTITLE_MSG = 0;
  private static final int METHODID_FINDCATEGORY_MSG = 1;
  private static final int METHODID_FINDACTOR_MSG = 2;
  private static final int METHODID_CREATE_MSG = 3;
  private static final int METHODID_UPDATE_MSG = 4;
  private static final int METHODID_DELETE_MSG = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FINDTITLE_MSG:
          serviceImpl.findtitleMsg((messages.Protocolo.FindTitle) request,
              (io.grpc.stub.StreamObserver<messages.Protocolo.Movie>) responseObserver);
          break;
        case METHODID_FINDCATEGORY_MSG:
          serviceImpl.findcategoryMsg((messages.Protocolo.FindCategory) request,
              (io.grpc.stub.StreamObserver<messages.Protocolo.FindResponse>) responseObserver);
          break;
        case METHODID_FINDACTOR_MSG:
          serviceImpl.findactorMsg((messages.Protocolo.FindActor) request,
              (io.grpc.stub.StreamObserver<messages.Protocolo.FindResponse>) responseObserver);
          break;
        case METHODID_CREATE_MSG:
          serviceImpl.createMsg((messages.Protocolo.Create) request,
              (io.grpc.stub.StreamObserver<messages.Protocolo.Movie>) responseObserver);
          break;
        case METHODID_UPDATE_MSG:
          serviceImpl.updateMsg((messages.Protocolo.Update) request,
              (io.grpc.stub.StreamObserver<messages.Protocolo.Movie>) responseObserver);
          break;
        case METHODID_DELETE_MSG:
          serviceImpl.deleteMsg((messages.Protocolo.Delete) request,
              (io.grpc.stub.StreamObserver<messages.Protocolo.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getFindtitleMsgMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              messages.Protocolo.FindTitle,
              messages.Protocolo.Movie>(
                service, METHODID_FINDTITLE_MSG)))
        .addMethod(
          getFindcategoryMsgMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              messages.Protocolo.FindCategory,
              messages.Protocolo.FindResponse>(
                service, METHODID_FINDCATEGORY_MSG)))
        .addMethod(
          getFindactorMsgMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              messages.Protocolo.FindActor,
              messages.Protocolo.FindResponse>(
                service, METHODID_FINDACTOR_MSG)))
        .addMethod(
          getCreateMsgMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              messages.Protocolo.Create,
              messages.Protocolo.Movie>(
                service, METHODID_CREATE_MSG)))
        .addMethod(
          getUpdateMsgMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              messages.Protocolo.Update,
              messages.Protocolo.Movie>(
                service, METHODID_UPDATE_MSG)))
        .addMethod(
          getDeleteMsgMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              messages.Protocolo.Delete,
              messages.Protocolo.Response>(
                service, METHODID_DELETE_MSG)))
        .build();
  }

  private static abstract class TesteServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TesteServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return messages.Protocolo.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TesteService");
    }
  }

  private static final class TesteServiceFileDescriptorSupplier
      extends TesteServiceBaseDescriptorSupplier {
    TesteServiceFileDescriptorSupplier() {}
  }

  private static final class TesteServiceMethodDescriptorSupplier
      extends TesteServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TesteServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TesteServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TesteServiceFileDescriptorSupplier())
              .addMethod(getFindtitleMsgMethod())
              .addMethod(getFindcategoryMsgMethod())
              .addMethod(getFindactorMsgMethod())
              .addMethod(getCreateMsgMethod())
              .addMethod(getUpdateMsgMethod())
              .addMethod(getDeleteMsgMethod())
              .build();
        }
      }
    }
    return result;
  }
}
