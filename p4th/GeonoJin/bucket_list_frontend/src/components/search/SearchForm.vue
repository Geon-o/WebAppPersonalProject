<template>
  <v-container>
    <tool-bar-component style="margin-top: 50px"/>
    <v-divider style="margin-top: 30px"></v-divider>
    <v-container>
      <h2 style="margin-left: 50px; margin-top: 30px">검색</h2>
    </v-container>

    <v-layout justify-end style="margin-top: 20px">
      <v-form style="width: 400px" @submit.prevent="onSearch">
        <v-layout>
          <v-text-field outlined dense placeholder="버킷리스트 제목을 입력하세요!" v-model="searchWord"/>
          <v-btn rounded-xl elevation="0" color="green lighten-1" style="margin-top: 2px" type="submit">
            검색
          </v-btn>
        </v-layout>
      </v-form>
    </v-layout>

    <v-layout justify-center style="margin-top: 50px">
      <div>
        <v-row class="ma-auto">
          <div v-if="!buckets || (Array.isArray(buckets) && buckets.length === 0)">
            <h3>현재 검색된 버킷스트가 없습니다.</h3>
          </div>
          <v-card
              v-else
              v-for="bucket in buckets" :key="bucket.bucketId"
              max-width="250"
              class="ma-5"
              v-show="bucket.switchValue === false"
          >
            <router-link :to="{name: 'BucketListReadView',
            params: { bucketId: bucket.bucketId.toString() }}"
                         style="text-decoration: none"
            >
              <v-img
                  src="@/assets/thumbnail/기본이미지.jpg"
                  class="white--text align-end"
                  gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                  height="200px"
                  contain
              >
                <v-card-title v-text="bucket.bucketTitle"></v-card-title>
              </v-img>
            </router-link>
          </v-card>
        </v-row>
      </div>
    </v-layout>
    <template>
      <div class="text-center">
        <v-pagination
            v-model="pageValue"
            :length="totalPage"
            @input="paging"
        ></v-pagination>
      </div>
    </template>
  </v-container>
</template>

<script>
import ToolBarComponent from "@/components/common/ToolBarComponent";
import {mapActions} from "vuex";

export default {
  name: "SearchForm",
  components: {ToolBarComponent},
  props: {
    buckets: {
      type: Array
    },
    totalPage:{
      type: Number
    }
  },
  data(){
    return{
      searchWord: '',
      pageValue: 1
    }
  },
  methods:{
    ...mapActions([
       'requestSearchBucketListToSpring'
    ]),
    onSearch(){
      const {searchWord, pageValue} = this
      this.$emit('search', {searchWord, pageValue})
    },
    async paging(){
      const {searchWord, pageValue} = this
      await this.requestSearchBucketListToSpring({searchWord, pageValue});
    }
  }
}
</script>

<style scoped>

</style>