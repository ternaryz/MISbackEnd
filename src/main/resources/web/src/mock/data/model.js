import Mock from 'mockjs';
import testImage from './../../assets/test1.png';



const Models = [];

for (let i = 0; i < 20; i++) {
  Models.push(Mock.mock({
    index: Mock.Random.guid(),
    name: Mock.Random.cname(),
    imageUrl: testImage
  }));
}


export {Models};